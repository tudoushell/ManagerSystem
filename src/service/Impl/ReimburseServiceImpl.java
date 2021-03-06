package service.Impl;

import beanfactory.BeanFactory;
import dao.ReimburseDao;
import entity.Reimburse;
import exception.ReimburseException;
import service.ReimburseService;
import util.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ReimburseServiceImpl implements ReimburseService {
    private ReimburseDao reimburseDao = (ReimburseDao) BeanFactory.getObject("reimbursedao");
    private Transaction transaction = (Transaction) BeanFactory.getObject("transaction");

    @Override
    public List<Reimburse> listReimburseByConditionOrAll(String[] columnName, boolean flag, Object... args)
            throws ReimburseException {
        List<Reimburse> listReimburse = reimburseDao.listReimburseByConditionOrAll(columnName, flag, args);
        if (listReimburse == null){
            throw new ReimburseException("没有报销信息");
        }
        return listReimburse;
    }

    @Override
    public boolean updateReimburse(Reimburse reimburse) {
        try {
            transaction.start();
            if(reimburseDao.updateReimburse(reimburse)){
                transaction.commit();
                return true;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getReimburseMaxId() {
        int count = reimburseDao.getReimburseMaxId();
        return count;
    }

    @Override
    public boolean saveReimburse(Reimburse reim) {
        try {
            transaction.start();
            boolean flag = reimburseDao.saveReimburse(reim);
            if(flag){
                transaction.commit();
                return true;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reimburse getReimburseByReimNo(String reimNo) {
         Reimburse reimburse = reimburseDao.getReimburseByReimNo(reimNo);
         if(reimburse != null){
             return  reimburse;
         }
        return null;
    }

    @Override
    public boolean deleteReimburseByReimNo(String reimNo) {
        try {
            transaction.start();
            boolean flag = reimburseDao.deleteReimburseByReimNo(reimNo);
            if(flag){
                transaction.commit();
                return flag;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int countReimburseByUser(String reimName, String reimType, String reimStatus) throws ReimburseException{
        try {
            transaction.start();
            int flag = reimburseDao.countReimburseByUser(reimName,reimType,reimStatus);
            if(flag != 0){
                transaction.commit();
                return flag;
            }else{
                throw new ReimburseException("没有此记录！");
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Reimburse> listReimburseByUser(int page,String reimName, String reimType, String reimStatus)
            throws ReimburseException{
        try {
            transaction.start();
            List<Reimburse> list  = reimburseDao.listReimburseByUser(page,reimName,reimType,reimStatus);
            if(list != null){
                transaction.commit();
                return list;
            }else {
                throw new ReimburseException("没有此报销单！");
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countReimburseByNames(String reinName) {
        try {
            transaction.start();
            int count = reimburseDao.countReimburseByName(reinName);
            if(count != 0){
                transaction.commit();
                return count;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Reimburse> listReimburseByNames(int page, String reinName) {
        try {
            transaction.start();
            List<Reimburse> list = reimburseDao.listReimburseByName(page,reinName);
            if(list != null){
                transaction.commit();
                return list;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countReimburseByCondition(String reimType, String reimStatus) throws ReimburseException{
        try {
            transaction.start();
            int result = reimburseDao.countReimburseByCondition(reimType,reimStatus);
            if(result != 0){
                transaction.commit();
                return result;
            }else {
                throw new ReimburseException("不在存报销");
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Reimburse> listReimburseByCondition(int page, String reimType, String reimStatus) {
        try {
            transaction.start();
            List<Reimburse> listReimburses = reimburseDao.listReimburseByCondition(page,reimType,reimStatus);
            if(listReimburses != null){
                transaction.commit();
                return listReimburses;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimburse> listReimburseByPage(int page) {
        try {
            transaction.start();
            List<Reimburse> listReimbursePage =  reimburseDao.listReimburseByPage(page);
            if(listReimbursePage != null){
                transaction.commit();
                return  listReimbursePage;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimburse> listReimburse() {
        try {
            transaction.start();
            List<Reimburse> listReimburses = reimburseDao.listReimburse();
            if(listReimburses != null){
                transaction.commit();
                return listReimburses;
            }
        } catch (SQLException e) {
            try {
                transaction.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
//        ReimburseService reimburseService = new ReimburseServiceImpl();
//        System.out.println(reimburseService.listReimburse().size());
        ReimburseService reimburseService = (ReimburseService)BeanFactory.getObject("reimburseservice");
        System.out.println(reimburseService.getReimburseByReimNo("3"));
    }
}
