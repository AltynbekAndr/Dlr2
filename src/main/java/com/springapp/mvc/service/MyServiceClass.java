package com.springapp.mvc.service;


import com.springapp.mvc.model_for_users.*;
import com.springapp.mvc.models2.Categories2;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class MyServiceClass {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory session;
    public void save(Courier courier){
        session.getCurrentSession().save(courier);
    }
    public void save(Company company){
        session.getCurrentSession().save(company);
    }
    public void save(Shops shops){
        session.getCurrentSession().save(shops);
    }
    public void save(GroupsUs groupsUs){
        session.getCurrentSession().save(groupsUs);
    }
    public void save(Groups groups){
        session.getCurrentSession().saveOrUpdate(groups);
    }
    public boolean save(UndGroups undGroups){
        session.getCurrentSession().save(undGroups);
        return true;
    }
    public void save(Categories categories){
        session.getCurrentSession().save(categories);
    }
    public void save(Products products,HttpSession httpSession,String product_category){
        Categories2[] categories2 = (Categories2[]) httpSession.getAttribute("categories");
        for(int i=0;i<categories2.length;i++){
            if(categories2[i].getCategory().equals(product_category)){
                Categories categories = new Categories();
                categories.setId(categories2[i].getId());
                products.setCategories(categories);
                break;
            }
        }
        session.getCurrentSession().save(products);
    }



    public List<Categories> getAllCategories(Company company){
        Criteria criteria = session.getCurrentSession().createCriteria(Categories.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        List<Categories> list = criteria.list();
        return list;
    }






    public Shops userVerificationShop(String login,String password){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.add(Restrictions.eq("login",login));
        criteria.add(Restrictions.eq("password", password));
        Shops shop = (Shops) criteria.uniqueResult();
        if(shop!=null){
            return shop;
        }else{
            return null;
        }
    }
    public Company userVerification(String login,String password){
        Criteria criteria = session.getCurrentSession().createCriteria(Company.class);
        criteria.add(Restrictions.eq("login",login));
        criteria.add(Restrictions.eq("password", password));
        Company company = (Company) criteria.uniqueResult();
        if(company!=null){
            return company;
        }else{
            return null;
        }
    }
    public DealerAdmin userVerification(DealerAdmin dealerAdmin){
        Criteria criteria = session.getCurrentSession().createCriteria(DealerAdmin.class);
        criteria.add(Restrictions.eq("login", dealerAdmin.getLogin()));
        criteria.add(Restrictions.eq("password", dealerAdmin.getPassword()));
        DealerAdmin dealerAdmin1 = (DealerAdmin) criteria.uniqueResult();
        if(dealerAdmin1 !=null){
            return dealerAdmin1;
        }else{
            return null;
        }
    }
    public Courier userVerificationCourier(String login,String password){
        Criteria criteria = session.getCurrentSession().createCriteria(Courier.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        criteria.createCriteria("company");
        Courier courierRes = (Courier) criteria.uniqueResult();
        if(courierRes !=null){
            return courierRes;
        }else{
            return null;
        }
    }

    public int maxCompanies() {
        Criteria c = session.getCurrentSession().createCriteria(Company.class);
        c.addOrder(Order.desc("id"));
        c.setMaxResults(1);
        Company company = (Company) c.uniqueResult();
        if(company!=null){
            int id = company.getId();
            return id;
        }
        return 0;

    }
/*=======================================================================================*/
    public boolean getLoginCompany(String login){
        Criteria criteria = session.getCurrentSession().createCriteria(Company.class);
        criteria.add(Restrictions.eq("login",login));
        Company company = (Company)criteria.uniqueResult();
        if(company!=null){
            return true;
        }else{
            return false;
        }
    }
    public boolean getLoginCourier(String login){
        Criteria criteria = session.getCurrentSession().createCriteria(Courier.class);
        criteria.add(Restrictions.eq("login",login));
        Courier couriers = (Courier)criteria.uniqueResult();
        if(couriers!=null){
            return true;
        }else{
            return false;
        }
    }
    public boolean getLoginShop(String login){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.add(Restrictions.eq("login", login));
        Shops shop = (Shops)criteria.uniqueResult();
        if(shop!=null){
            return true;
        }else{
            return false;
        }
    }
/*=======================================================================================*/

/*==================================CATEGORIES=========================================*/

    public boolean delCategory(Categories categories){
        session.getCurrentSession().remove(categories);
        return true;
    }
    public boolean updateCategory(Categories categories){
        session.getCurrentSession().update(categories);
        return true;
    }
    public Categories getCategorybyId(int id){
        Categories categories = session.getCurrentSession().get(Categories.class,id);
        return categories;
    }

/*================================== ^ CATEGORIES ^ =========================================*/


/*==================================PRODUCTS=========================================*/

    public String delProduct(Products products){
        session.getCurrentSession().delete(products);
        return  "success";
    }
    public void updateProduct(Products products,HttpSession httpSession,String product_category){
        Categories2[] categories2 = (Categories2[]) httpSession.getAttribute("categories");
        for(int i=0;i<categories2.length;i++){
            if(categories2[i].getCategory().equals(product_category)){
                Categories categories = new Categories();
                categories.setId(categories2[i].getId());
                products.setCategories(categories);
                break;
            }
        }
        session.getCurrentSession().update(products);
    }


    public Products getProducts(Products products){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.add(Restrictions.eq("company.id", products.getCompany().getId()));
        criteria.add(Restrictions.eq("id", products.getId()));
        Products products1 = (Products) criteria.uniqueResult();
        return products1;
    }

    public List<Products> getAllProducts(Company company){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("status", false));
        criteria.addOrder(Order.desc("id"));
        List<Products> list = criteria.list();
        return list;
    }

    public List<Products> getAllProductsByCategoryId(Company company,Categories categories){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("categories.id", categories.getId()));
        criteria.createCriteria("categories");
        criteria.addOrder(Order.desc("id"));
        List<Products> list = criteria.list();
        return list;
    }

/*================================== ^ PRODUCTS ^ =========================================*/






/*==================================COURIER=========================================*/
    public List<Courier> getAllCourier(Company company){
         Query query = session.getCurrentSession().createSQLQuery("select * from courier where courier_company_id="+company.getId()).addEntity(Courier.class);
         List<Courier> couriers = query.list();
        return couriers;
    }
    public Courier getCourierById(Company company,int courier_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Courier.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("id", courier_id));
        Courier courier = (Courier)criteria.uniqueResult();
        return courier;
    }
    public boolean updateCourier(Courier courier){
        session.getCurrentSession().update(courier);
        return true;
    }
    public void delCourier(Courier courier){
            session.getCurrentSession().delete(courier);
    }
/*================================== ^ COURIER ^ =========================================*/








/*==================================ORDER=========================================*/
    public List<MyOrder> myOrderListToHistory(Company company){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status","completed"));
        criteria.setMaxResults(30);
        criteria.addOrder(Order.desc("id"));
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public List<MyOrder> myOrderListToHistory(Company company,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status", "completed"));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(first);
        criteria.setMaxResults(30);
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public List<MyOrder> myOrderListNew(Company company){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status", "new"));
        criteria.setMaxResults(30);
        criteria.addOrder(Order.desc("id"));
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public int getSizeToPagination(Company company){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select myOrder_id from myorder where status='new' and myorder_company_id=" + company.getId());
        List l = sqlQuery.list();
        return l.size();
    }
    public int getSizeToPaginationAccepted(Company company){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select myOrder_id from myorder where status='accepted' and myorder_company_id="+company.getId());
        List l = sqlQuery.list();
        return l.size();
    }
    public int getSizeToPaginationCompleted(Company company){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select myOrder_id from myorder where status='completed' and myorder_company_id="+company.getId());
        List l = sqlQuery.list();
        return l.size();
    }

    public List<MyOrder> myOrderListNew(Company company,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status", "new"));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(first);
        criteria.setMaxResults(30);
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public List<MyOrder> myOrderListAccepted(Company company){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status", "accepted"));
        criteria.setMaxResults(30);
        criteria.addOrder(Order.desc("id"));
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public List<MyOrder> myOrderListAccepted(Company company,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status", "accepted"));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(first);
        criteria.setMaxResults(30);
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public List<MyOrder> myOrderListComplete(Company company){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status", "completed"));
        criteria.setMaxResults(30);
        criteria.addOrder(Order.desc("id"));
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public List<MyOrder> myOrderListComplete(Company company,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.add(Restrictions.eq("status", "completed"));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(first);
        criteria.setMaxResults(30);
        List<MyOrder> myOrders = criteria.list();
        return myOrders;
    }
    public List<Products> getOrderById(int orderId,int companyId){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class,"product");
        criteria.add(Restrictions.eq("myorder.id", orderId));
        criteria.createAlias("myorder", "m");
        criteria.createCriteria("m.shops");

        List<Products> productses = criteria.list();
        return productses;
    }
    public List<Products> getOrderByIdWithCourier(int orderId,int companyId){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class,"product");
        criteria.add(Restrictions.eq("myorder.id", orderId));
        criteria.createAlias("myorder", "m");
        criteria.createCriteria("m.shops");
        criteria.createCriteria("m.courier");

        List<Products> productses = criteria.list();
        return productses;
    }
    public boolean updateOrderById(MyOrder myOrder){
        session.getCurrentSession().update(myOrder);
        return true;
    }
    public List<Products> getOrdersToMap(int companyId){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class,"product");
        criteria.add(Restrictions.eq("status", true));
        criteria.add(Restrictions.eq("company.id",companyId));
        criteria.createAlias("myorder", "m");
        criteria.createCriteria("m.shops");
        List<Products> productses = criteria.list();
        return productses;
    }
    public List<MyOrder> getOrderConcreteShop(int shopId,int compId){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class,"order");
        criteria.add(Restrictions.disjunction()
                        .add(Restrictions.eq("status", "new"))
                        .add(Restrictions.eq("status", "accepted"))
        );
        criteria.add(Restrictions.eq("shops.id", shopId));
        criteria.add(Restrictions.eq("company.id", compId));
        criteria.setMaxResults(100);
        criteria.addOrder(Order.desc("id"));
        List<MyOrder> orders = criteria.list();
        return orders;
    }
/*================================== ^ ORDER ^ =========================================*/


/*====================================GROUPS=============================================*/

    public List<GroupsUs> getGlobalGroups(){
        Criteria criteria = session.getCurrentSession().createCriteria(GroupsUs.class);
        List<GroupsUs> groupsUs = (List<GroupsUs>)criteria.list();
        return groupsUs;
    }
    public GroupsUs getGlobalGroupById(int id){
        Criteria criteria = session.getCurrentSession().createCriteria(GroupsUs.class);
        criteria.add(Restrictions.eq("id",id));
        GroupsUs groupsUs = (GroupsUs)criteria.uniqueResult();
        return groupsUs;
    }
    public List<Groups> getGroups(Company company){
       Criteria criteria = session.getCurrentSession().createCriteria(Groups.class);
       criteria.add(Restrictions.eq("company.id",company.getId()));
       List<Groups> groupses =(List<Groups>) criteria.list();
        return groupses;
    }
    public List<UndGroups> getShopsWithUndGroups(int companyId){
        Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
        criteria.add(Restrictions.eq("company.id", companyId));
        criteria.createCriteria("shops");
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(30);
        List<UndGroups> groupses =(List<UndGroups>) criteria.list();
        return groupses;
    }
    public List<UndGroups> getShopsWithUndGroups2(int companyId,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
        criteria.add(Restrictions.eq("company.id",companyId));
        criteria.createCriteria("shops");
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(first);
        criteria.setMaxResults(30);
        List<UndGroups> groupses =(List<UndGroups>) criteria.list();
        return groupses;
    }
    public List<UndGroups> getShopsWithUndGroups(int companyId,int groupsId){
       Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
       criteria.add(Restrictions.eq("company.id",companyId));
       criteria.add(Restrictions.eq("groups.id",groupsId));
       criteria.createCriteria("shops");
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(30);
       List<UndGroups> groupses =(List<UndGroups>) criteria.list();
        return groupses;
    }
    public List<UndGroups> getShopsWithUndGroups(int companyId,int groupsId,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
        criteria.add(Restrictions.eq("company.id",companyId));
        criteria.add(Restrictions.eq("groups.id",groupsId));
        criteria.createCriteria("shops");
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(first);
        criteria.setMaxResults(30);
        List<UndGroups> groupses =(List<UndGroups>) criteria.list();
        return groupses;
    }
    public List<UndGroups> getShopsWithUndGroupsForSearch(int companyId,String shop_name){
        Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
        criteria.add(Restrictions.eq("company.id",companyId));
        criteria.createAlias("shops", "s");
        criteria.add(Restrictions.like("s.name","%"+shop_name+"%"));
        List<UndGroups> groupses =(List<UndGroups>) criteria.list();
        return groupses;
    }
    public List<UndGroups> getListUndGroups(int companyId){
       Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
       criteria.add(Restrictions.eq("company.id",companyId));
       List<UndGroups> groupses =(List<UndGroups>) criteria.list();
        return groupses;
    }
    public int getSizeToPaginationShops(List<Integer> undGroupsShopsId){
        StringBuilder sql = new StringBuilder("SELECT id FROM shops WHERE shop_group_us_id=1 AND id NOT IN (");
        for(int i=0;i<undGroupsShopsId.size();i++){
            sql.append(""+undGroupsShopsId.get(i)+",");
        }
        sql.append("0)");
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql.toString());
        int size = sqlQuery.list().size();
        return size;
    }
    public int getSizeToPaginationShops(int group_id,List<Integer> undGroupsShopsId){
        StringBuilder sql = new StringBuilder("SELECT id FROM shops WHERE shop_group_us_id="+group_id+" and  id NOT IN (");
        for(int i=0;i<undGroupsShopsId.size();i++){
            sql.append(""+undGroupsShopsId.get(i)+",");
        }
        sql.append("0)");
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql.toString());
        int size = sqlQuery.list().size();
        return size;
    }
    public Groups getGroupById(int groupId){
        Criteria criteria = session.getCurrentSession().createCriteria(Groups.class);
        criteria.add(Restrictions.eq("id",groupId));
        Groups groups = (Groups)criteria.uniqueResult();
        return groups;
    }
    public boolean updateGroup(Groups groups){
        session.getCurrentSession().update(groups);
        return true;
    }
    public boolean delGroup(Groups groups){
        session.getCurrentSession().remove(groups);
        return true;
    }

    public boolean updateUndGroupPlus(int shop_id,float debt){
            String sql = "UPDATE undgroups SET shopDebt = shopDebt + "+debt+" WHERE shops_id="+shop_id;
            SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
            sqlQuery.executeUpdate();
        return true;
    }

    public boolean updateUndGroupMinus(int comp_id,int shop_id,float debt){
        String sql = "UPDATE undgroups SET shopDebt = shopDebt - "+debt+" WHERE shops_id="+shop_id+" AND company_id="+comp_id;
        SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        return true;
    }
    public List<UndGroups> getDebtShops(int companyId){
            if(companyId!=0){
                Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
                criteria.add(Restrictions.eq("company.id", companyId));
                criteria.createCriteria("shops");
                criteria.add(Restrictions.disjunction()
                                .add(Restrictions.not(Restrictions.in("shopDebt", 0f)))
                );
                List<UndGroups> undGroupsWithShopsDebt = (List<UndGroups>) criteria.list();
                return undGroupsWithShopsDebt;
            }
    return null;
    }




/*================================== ^ GROUPS ^ =========================================*/








/*=====================================UND_GROUPS=========================================*/

    public int getSizeToPaginationUndGroupsToDebt(Company company){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select id from undgroups where company_id="+company.getId()+" and shopdebt > 0");
        List l = sqlQuery.list();
        return l.size();
    }
    public int getSizeToPaginationUndGroups(Company company){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select id from undgroups where company_id="+company.getId());
        List l = sqlQuery.list();
        return l.size();
    }
    public int getSizeToPaginationUndGroupsOnGroup(Company company,int groupId){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select id from undgroups where company_id="+company.getId()+" and groups_id="+groupId);
        List l = sqlQuery.list();
        return l.size();
    }

    public boolean update_und_group(UndGroups undGroups){
        String sql = "UPDATE undgroups set shopcons="+undGroups.isShopCons()+" where id="+undGroups.getId();
        SQLQuery query = session.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
        return true;
    }

    public List<UndGroups> getDebtShopsTopage(int companyId){
        Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
        criteria.add(Restrictions.eq("company.id",companyId));
        criteria.createCriteria("groups");
        criteria.createCriteria("shops");
        criteria.setMaxResults(30);
        criteria.add(Restrictions.sqlRestriction("shopDebt > 0 "));

        List<UndGroups> undGroups =criteria.list();
        return undGroups;
    }
    public List<UndGroups> getDebtShopsTopage(int companyId,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
        criteria.add(Restrictions.eq("company.id",companyId));
        criteria.createCriteria("groups");
        criteria.createCriteria("shops");
        criteria.setFirstResult(first);
        criteria.setMaxResults(30);
        criteria.add(Restrictions.sqlRestriction("shopDebt > 0 "));

        List<UndGroups> undGroups =criteria.list();
        return undGroups;
    }





/*================================== ^ UND_GROUPS ^ =========================================*/



















/*==================================SHOPS=========================================*/

    public int getSizeToPaginationShops(int group_id){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select id from shops where shop_group_us_id="+group_id);
        List l = sqlQuery.list();
        return l.size();
    }

    public List<Shops> getAllShopsByGroupsUsId(int groupsUs){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.add(Restrictions.eq("groupsus.id", groupsUs));
        criteria.setMaxResults(30);
        List<Shops> list = criteria.list();
        return list;
    }
    public List<Shops> getAllShopsByGroupsUsId(int groupsUs,List<Integer> shopsId){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.add(Restrictions.eq("groupsus.id", groupsUs));
        criteria.setMaxResults(30);
        criteria.add(Restrictions.not(Restrictions.in("id",shopsId)));
        List<Shops> list = criteria.list();
        return list;
    }
    public List<Shops> getAllShopsByGroupsUsId(int groupsUs,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.add(Restrictions.eq("groupsus.id", groupsUs));
        criteria.setMaxResults(30);
        criteria.setFirstResult(first);
        List<Shops> list = criteria.list();
        return list;
    }
    public List<Shops> getAllShopsByGroupsUsId(int groupsUs,List<Integer> shopsId,int first){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.add(Restrictions.eq("groupsus.id", groupsUs));
        criteria.setMaxResults(30);
        criteria.setFirstResult(first);
        criteria.add(Restrictions.not(Restrictions.in("id",shopsId)));
        List<Shops> list = criteria.list();
        return list;
    }
    public Shops getShopByid(int shopId){
       Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
       criteria.add(Restrictions.eq("id",shopId));
        criteria.createCriteria("groupsus");
       Shops shops = (Shops) criteria.uniqueResult();
      if (shops!=null){
          return shops;
      }else{
          return null;
      }
    }
    public boolean delShop(Shops shop){
        session.getCurrentSession().remove(shop);
        return true;
    }

    public List<Shops> searchShops(String shopName,List<Integer> shopsId){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.add(Restrictions.like("name", "%"+shopName+"%"));
        criteria.add(Restrictions.not(Restrictions.in("id", shopsId)));
        List<Shops> list = criteria.list();
        return list;
    }




/*================================== ^ SHOPS ^ =========================================*/

/*=====================================SHOPS_ADMIN=========================================*/

    public List<Shops> getAllShopsToAdmin(){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.addOrder(Order.desc("id"));
        List<Shops> list = criteria.list();
        return list;
    }
    public boolean updateShop(Shops shops){
        session.getCurrentSession().update(shops);
        return true;
    }



/*================================== ^ SHOPS_ADMIN ^ =========================================*/
/*=====================================COMPANY_ADMIN=========================================*/

    public Company getCompanyById(int compId){
        Criteria criteria = session.getCurrentSession().createCriteria(Company.class);
        criteria.add(Restrictions.eq("id",compId));
        Company company = (Company) criteria.uniqueResult();
        return company;
    }

/*================================== ^ COMPANY_ADMIN ^ =========================================*/

/*==================================  ORDER_PDF    =========================================*/
    public List<MyOrder> ordersList(String fDate,String sDate,Company company){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("company.id", company.getId()));
        criteria.add(Restrictions.between("date", fDate, sDate));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.createCriteria("courier");
        criteria.createCriteria("shops");
        List <MyOrder> myOrders = criteria.list();
        return myOrders;
    }
/*================================== ^ ORDER_PDF ^ =========================================*/


/*==================================  COMPANIES    =========================================*/
    public List<Company> getCompaniesToAdmin(){
        Criteria criteria = session.getCurrentSession().createCriteria(Company.class);
        criteria.addOrder(Order.desc("id"));
        List<Company> companies = criteria.list();
        return companies;
    }
    public boolean updateCompany(Company company){
        session.getCurrentSession().update(company);
        return true;
    }
    public boolean removeCompany(Company company){
        session.getCurrentSession().remove(company);
        return true;
    }
/*================================== ^ COMPANIES ^ =========================================*/









/*==================================FOR REST API=========================================*/
    public List<UndGroups> getCompaniesAndGroups(int shopId){
        Criteria criteria = session.getCurrentSession().createCriteria(UndGroups.class);
        criteria.add(Restrictions.eq("shops.id",shopId));
        criteria.createAlias("company","c");
        criteria.createCriteria("c.groups");
        criteria.createCriteria("shops");
        List<UndGroups> undGroups = criteria.list();
        return undGroups;
    }

    public List<Categories> getCompanyCategories(String company_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Categories.class);
        criteria.add(Restrictions.eq("company.id", Integer.valueOf(company_id)));
        criteria.addOrder(Order.desc("id"));
        List<Categories> categorieses = criteria.list();
        return categorieses;
    }


    public List<Products> getCompanyProducts(String company_id,String categories_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.add(Restrictions.eq("company.id", Integer.valueOf(company_id)));
        criteria.add(Restrictions.eq("categories.id", Integer.valueOf(categories_id)));
        criteria.addOrder(Order.desc("id"));
        List<Products> products = criteria.list();
        return products;
    }

    public boolean createNewOrder(MyOrder myOrder){
        session.getCurrentSession().save(myOrder);
        return true;
    }
    public List<Shops> getAllShopsToAdminAndroid(){
        Criteria criteria = session.getCurrentSession().createCriteria(Shops.class);
        criteria.createCriteria("groupsus");
        criteria.setMaxResults(200);
        criteria.addOrder(Order.desc("id"));
        List<Shops> list = criteria.list();
        return list;
    }
    public List<MyOrder> getOrders(int shopId){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("shops.id",shopId));
        criteria.add(Restrictions.eq("selected", false));
        criteria.add(Restrictions.eq("hide", false));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.createCriteria("company");
        List<MyOrder> orders  = criteria.list();
        return orders;
    }
    public List<MyOrder> getOrdersSelected(int shopId){
        Criteria criteria = session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("shops.id",shopId));
        criteria.add(Restrictions.eq("selected", true));
        criteria.add(Restrictions.eq("hide", false));
        criteria.add(Restrictions.eq("removeorder", false));
        criteria.createCriteria("company");
        List<MyOrder> orders  = criteria.list();
        return orders;
    }
    public List<Products> getOrderByIdToRest(int shop_id,int order_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class,"p");
        criteria.createAlias("p.myorder", "m");
        criteria.add(Restrictions.eq("m.id", order_id));
        criteria.createAlias("m.shops", "s");
        criteria.add(Restrictions.eq("s.id", shop_id));
        List<Products> products = criteria.list();
        return products;
    }
    public List<Products> getOrderByIdToRestWithCourier(int shop_id,int order_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class,"p");
        criteria.createAlias("p.myorder", "m");
        criteria.add(Restrictions.eq("m.id", order_id));
        criteria.createAlias("m.shops", "s");
        criteria.add(Restrictions.eq("s.id", shop_id));
        criteria.createCriteria("m.courier");
        List<Products> products = criteria.list();
        return products;
    }
    public boolean selectOrder(int order_id){
        String sql = "UPDATE myorder SET selected=true WHERE myorder_id="+order_id;
        SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        return true;
    }
    public boolean deselectOrder(int order_id){
        String sql = "UPDATE myorder SET selected=false WHERE myorder_id="+order_id;
        SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        return true;
    }
    public boolean removeOrder(int order_id){
        String sql = "UPDATE myorder SET removeorder=true WHERE myorder_id="+order_id;
        SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        return true;
    }
    public boolean hideOrder(int order_id){
        String sql = "UPDATE myorder SET hide=true WHERE myorder_id="+order_id;
        SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        return true;
    }
    public boolean order_change_amount(int product_id,int product_amount){
        String sql = "UPDATE products SET acceptAmount="+product_amount+",productAccepted=true WHERE id="+product_id;
        SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        return true;
    }
/*================================== ^ FOR REST API ^ =========================================*/















/*==================================FOR REST API COURIER=========================================*/

    public List<MyOrder> getAcceptedOrdersToCourier(int courierId){
      Criteria criteria =  session.getCurrentSession().createCriteria(MyOrder.class);
      criteria.add(Restrictions.eq("courier.id",courierId));
      criteria.add(Restrictions.eq("status", "accepted"));
      criteria.createCriteria("shops");
      List<MyOrder> orders = criteria.list();
        return orders;
    }
    public List<MyOrder> getCompletedOrdersToCourier(int courierId){
        Criteria criteria =  session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("courier.id",courierId));
        criteria.add(Restrictions.eq("status","completed"));
        criteria.createCriteria("shops");
        List<MyOrder> orders = criteria.list();
        return orders;
    }

    public List<Products> getOrderWithProducts(int order_id){
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.add(Restrictions.eq("myorder.id", order_id));
        List<Products> productses = criteria.list();
        return productses;
    }

    public boolean order_completed(int order_id){
        String sql = "UPDATE myorder SET status='completed' WHERE myorder_id="+order_id;
        SQLQuery sqlQuery =session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        return true;
    }

    public List<MyOrder> getAcceptedOrdersToCourierMap(int courierId){
        Criteria criteria =  session.getCurrentSession().createCriteria(MyOrder.class);
        criteria.add(Restrictions.eq("courier.id",courierId));
        Restrictions.eq("status", "accepted");
        criteria.createCriteria("shops");
        List<MyOrder> orders = criteria.list();
        return orders;
    }
/*================================== ^ FOR REST API COURIER^ =========================================*/








    public void updaterows(){
        /*SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("delete from products where id between 901 and 980");
        sqlQuery.executeUpdate();*/

        /*SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("UPDATE myorder SET status = 'new' WHERE status = 'accepted' or status='completed' ");
        sqlQuery.executeUpdate();*/

        /*SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("UPDATE myorder SET status = 'new' WHERE courier_id is not null  and status = 'accepted' ");
        sqlQuery.executeUpdate();*/

        /*SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("UPDATE myorder SET status = 'completed' WHERE  status = 'accepted' ");
        sqlQuery.executeUpdate();*/

        /*SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("UPDATE myorder SET status = 'accepted' WHERE  status = 'completed' ");
        sqlQuery.executeUpdate();*/

        /*SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("UPDATE myorder SET status = 'completed' WHERE status = 'accepted' or status='new' ");
        sqlQuery.executeUpdate();*/

        /*SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("UPDATE shops SET shop_group_us_id = 3 where id between 100 and 200");
        sqlQuery.executeUpdate();*/


    }











}
