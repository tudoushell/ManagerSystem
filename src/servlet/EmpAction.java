package servlet;

import beanfactory.BeanFactory;
import entity.Employee;
import exception.EmpException;
import service.EmpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpAction {
    private EmpService empService = (EmpService) BeanFactory.getObject("empservice");





    /**
     * 1.列出所有的员工
     * 2.按条件来列出员工
     * @param request
     * @param response
     * @return
     */
    public String doList(HttpServletRequest request , HttpServletResponse response){
        //获取员工姓名
        String empName = null;
        String result = null;
        empName = request.getParameter("empName");
        if(empName.length() > 0){
            result =  doEmpCondition(request,response);
        }else {
            result = listEmp(request,response);
            System.out.println(result);
        }
        return result;
    }

    /**
     * 按条件来列出员工并进行分页
     * @param request
     * @param response
     * @return
     */
    public String doEmpCondition(HttpServletRequest request , HttpServletResponse response){
        String empName = request.getParameter("empName");
        String empDept = request.getParameter("empDept");

        int COUNT = 3;
        int page = Integer.parseInt(request.getParameter("page"));
        int sum = 0;

        //判断异常
        try {
            sum = empService.countEmpByConditions(empName,empDept);
        } catch (EmpException e) {
            request.setAttribute("result", e.getErrorMsg());
            request.setAttribute("method","empList.do?page=1");
            return "fail";
        }

        Map<String,Integer> map = divisionPage(COUNT,page,sum);

        int allPages = map.get("allPages");
        page = map.get("page");

        //按条数获取员工数
        List<Employee> allEmp = empService.listEmpByConditions((page - 1) * COUNT,empName,empDept);
        request.setAttribute("page",page);
        //尾页
        request.setAttribute("allPage",allPages);
        request.setAttribute("listEmp",allEmp);
        //设置原来的值
        request.setAttribute("empNames",empName);
        request.setAttribute("empDepts",empDept);
        return "success";
    }



    /**
     * 列出所有员工
     * @param request
     * @param response
     * @return
     */
    public String listEmp(HttpServletRequest request , HttpServletResponse response){
        //每页显示的条数
        int COUNT = 3;
        //用户页数
        int page = Integer.parseInt(request.getParameter("page"));
        //获取员工总数
        int sum  = empService.listEmps().size();

        Map<String,Integer> map = divisionPage(3,page,sum);
        int allPages = map.get("allPages");
        page = map.get("page");

        //按条数获取员工数
        List<Employee> allEmp = empService.listEmpByPages((page - 1) * COUNT);
        request.setAttribute("page",page);
        //尾页
        request.setAttribute("allPage",allPages);
        request.setAttribute("listEmp",allEmp);
        return "success";
    }




    /*
        工具类
     */
    /**
     *
     * @param count 分页数
     * @param page  用户要的页数
     * @param sum  总共的页数
     * @return
     */
    public Map<String,Integer> divisionPage(int count , int page , int sum){
            Map<String,Integer> map = new HashMap<>();
            //员工总条数
            int allPages = sum / count + (sum % count ==0 ? 0 : 1);
            //进行页数判断
            if(page < 1){
                page = 1;
            }else if (page > allPages){
                page = allPages;
            }
            map.put("allPages",allPages);
            map.put("page",page);
        return map;
    }
}
