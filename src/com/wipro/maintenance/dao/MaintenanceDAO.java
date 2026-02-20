package com.wipro.maintenance.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.maintenance.bean.MaintenanceBean;
import com.wipro.maintenance.util.DBUtil;

public class MaintenanceDAO {

    // INSERT RECORD
    public String createRecord(MaintenanceBean bean) {

        String status = "FAIL";

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "insert into MAINTENANCE_TB values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bean.getRequestId());
            ps.setString(2, bean.getRequesterName());
            ps.setString(3, bean.getIssueType());
            ps.setDate(4, new java.sql.Date(bean.getRequestDate().getTime()));
            ps.setString(5, bean.getPriority());
            ps.setString(6, bean.getRemarks());

            int row = ps.executeUpdate();

            if (row > 0) {
                status = bean.getRequestId();
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // CHECK RECORD EXISTS
    public boolean recordExists(String requesterName, Date requestDate) {

        boolean flag = false;

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "select * from MAINTENANCE_TB where REQUESTERNAME=? and REQUESTDATE=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, requesterName);
            ps.setDate(2, new java.sql.Date(requestDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                flag = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    // GENERATE REQUEST ID
    public String generateRequestID(String requesterName, Date requestDate) {

        String id = "";

        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            String datePart = f.format(requestDate);

            String namePart = requesterName.substring(0, 2).toUpperCase();

            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select MAINTENANCE_SEQ.nextval from dual");

            int seq = 0;
            if (rs.next()) {
                seq = rs.getInt(1);
            }

            id = datePart + namePart + seq;

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

public MaintenanceBean getRecord(String requesterName, Date requestDate) {

    MaintenanceBean bean = null;

    try {
        Connection con = DBUtil.getDBConnection();

        String sql = "select * from MAINTENANCE_TB where REQUESTERNAME=? and REQUESTDATE=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, requesterName);
        ps.setDate(2, new java.sql.Date(requestDate.getTime()));

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            bean = new MaintenanceBean();

            bean.setRequestId(rs.getString(1));
            bean.setRequesterName(rs.getString(2));
            bean.setIssueType(rs.getString(3));
            bean.setRequestDate(rs.getDate(4));
            bean.setPriority(rs.getString(5));
            bean.setRemarks(rs.getString(6));
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return bean;
}
public List<MaintenanceBean> getAllRecords() {

    List<MaintenanceBean> list = new ArrayList<>();

    try {
        Connection con = DBUtil.getDBConnection();

        String sql = "select * from MAINTENANCE_TB";
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            MaintenanceBean bean = new MaintenanceBean();

            bean.setRequestId(rs.getString(1));
            bean.setRequesterName(rs.getString(2));
            bean.setIssueType(rs.getString(3));
            bean.setRequestDate(rs.getDate(4));
            bean.setPriority(rs.getString(5));
            bean.setRemarks(rs.getString(6));

            list.add(bean);
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

}

