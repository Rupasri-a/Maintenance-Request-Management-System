package com.wipro.maintenance.service;

import com.wipro.maintenance.bean.MaintenanceBean;
import com.wipro.maintenance.dao.MaintenanceDAO;
import com.wipro.maintenance.util.InvalidInputException;

public class Administrator {

    MaintenanceDAO dao = new MaintenanceDAO();

    public String addRecord(MaintenanceBean bean) {

        try {

            if (bean == null ||
                bean.getRequesterName() == null ||
                bean.getIssueType() == null ||
                bean.getRequestDate() == null) {

                throw new InvalidInputException();
            }

            if (bean.getRequesterName().length() < 2) {
                return "INVALID REQUESTER NAME";
            }

            if (bean.getIssueType().length() < 2) {
                return "INVALID ISSUE TYPE";
            }

            if (bean.getPriority().length() < 2) {
                return "INVALID PRIORITY";
            }

            if (dao.recordExists(bean.getRequesterName(), bean.getRequestDate())) {
                return "ALREADY EXISTS";
            }

            String id = dao.generateRequestID(bean.getRequesterName(), bean.getRequestDate());
            bean.setRequestId(id);

            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }
    
}
