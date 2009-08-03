/*
 *  This file is part of MP.
 *
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.db.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.Formattable;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryHandler;
import mpv5.globals.Messages;
import mpv5.handler.FormatHandler;
import mpv5.handler.MPEnum;
import mpv5.logging.Log;
import mpv5.ui.panels.ItemPanel;
import mpv5.utils.images.MPIcon;

/**
 *
 *  
 */
public class Item extends DatabaseObject implements Formattable {

    /**
     * Returns a localized string represenation of the given item status
     * @param status
     * @return
     */
    public static String getStatusString(int status) {
        switch (status) {
            case (STATUS_QUEUED):
                return Messages.STATUS_QUEUED.toString();
            case (STATUS_IN_PROGRESS):
                return Messages.STATUS_IN_PROGRESS.toString();
            case (STATUS_PAUSED):
                return Messages.STATUS_PAUSED.toString();
            case (STATUS_FINISHED):
                return Messages.STATUS_FINISHED.toString();
            case (STATUS_PAID):
                return Messages.STATUS_PAID.toString();
            case (STATUS_CANCELLED):
                return Messages.STATUS_CANCELLED.toString();
        }
        return null;
    }

    /**
     * Returns all possible status messages
     * @return
     */
    public static MPEnum[] getStatusStrings() {
        MPEnum[] en = new MPEnum[6];
        en[0] = new MPEnum() {

            @Override
            public Integer getId() {
                return new Integer(STATUS_QUEUED);
            }

            @Override
            public String getName() {
                return getStatusString(getId());
            }
        };
        en[1] = new MPEnum() {

            @Override
            public Integer getId() {
                return new Integer(STATUS_IN_PROGRESS);
            }

            @Override
            public String getName() {
                return getStatusString(getId());
            }
        };
        en[2] = new MPEnum() {

            @Override
            public Integer getId() {
                return new Integer(STATUS_PAUSED);
            }

            @Override
            public String getName() {
                return getStatusString(getId());
            }
        };
        en[3] = new MPEnum() {

            @Override
            public Integer getId() {
                return new Integer(STATUS_FINISHED);
            }

            @Override
            public String getName() {
                return getStatusString(getId());
            }
        };
        en[4] = new MPEnum() {

            @Override
            public Integer getId() {
                return new Integer(STATUS_PAID);
            }

            @Override
            public String getName() {
                return getStatusString(getId());
            }
        };
        en[5] = new MPEnum() {

            @Override
            public Integer  getId() {
                return new Integer(STATUS_CANCELLED);
            }

            @Override
            public String getName() {
                return getStatusString(getId());
            }
        };
        return en;
    }

    /**
     * Returns a localized string represenation of the given item type
     * @param type
     * @return
     */
    public static String getTypeString(int type) {
        switch (type) {
            case (TYPE_BILL):
                return Messages.TYPE_BILL.toString();
            case (TYPE_OFFER):
                return Messages.TYPE_OFFER.toString();
            case (TYPE_ORDER):
                return Messages.TYPE_ORDER.toString();
        }
        return null;
    }
    private static HashMap<Integer, Double> taxes = new HashMap<Integer, Double>();

    /**
     * Tries to fetch the value for the given tax id
     * @param taxid
     * @return A value or 0d if not found
     */
    public static Double getTaxValue(Integer taxid) {
   
        if (taxid.intValue() > 0) {
            if (taxes.containsKey(taxid)) {
                return taxes.get(taxid);
            } else {
                try {
                    double v = Double.valueOf(QueryHandler.instanceOf().clone(Context.getTaxes()).select("taxvalue", new String[]{"ids", taxid.toString(), ""})[0][0].toString());
                    taxes.put(taxid, v);
                    return v;
                } catch (NumberFormatException numberFormatException) {
                    return 0d;
                }
            }
        } else {
            return 0.0;
        }
    }
    private int contactsids;
    private int defaultaccountsids;
    private double netvalue;
    private double taxvalue;
    private Date datetodo;
    private Date dateend;
    private int intreminders;
    private int intstatus;
    private int inttype;
    private String description = "";
    private String cnumber = "";
    public static final int STATUS_QUEUED = 0;
    public static final int STATUS_IN_PROGRESS = 1;
    public static final int STATUS_PAUSED = 2;
    public static final int STATUS_FINISHED = 3;
    public static final int STATUS_PAID = 4;
    public static final int STATUS_CANCELLED = 5;
    public static final int TYPE_BILL = 0;
    public static final int TYPE_ORDER = 1;
    public static final int TYPE_OFFER = 2;
    private FormatHandler formatHandler;

    public Item() {
        context.setDbIdentity(Context.IDENTITY_ITEMS);
        context.setIdentityClass(Item.class);
    }

    /**
     * @return the contactsids
     */
    public int __getContactsids() {
        return contactsids;
    }

    /**
     * @param contactsids the contactsids to set
     */
    public void setContactsids(int contactsids) {
        this.contactsids = contactsids;
    }

    /**
     * @return the taxvalue
     */
    public double __getTaxvalue() {
        return taxvalue;
    }

    /**
     * @param taxvalue the taxvalue to set
     */
    public void setTaxvalue(double taxvalue) {
        this.taxvalue = taxvalue;
    }

    /**
     * @return the datetodo
     */
    public Date __getDatetodo() {
        return datetodo;
    }

    /**
     * @param datetodo the datetodo to set
     */
    public void setDatetodo(Date datetodo) {
        this.datetodo = datetodo;
    }

    /**
     * @return the intreminders
     */
    public int __getIntreminders() {
        return intreminders;
    }

    /**
     * @param intreminders the intreminders to set
     */
    public void setIntreminders(int intreminders) {
        this.intreminders = intreminders;
    }

//    /**
//     * @return the groupname
//     */
////    public String __getGroupname() {
//        return groupname;
//    }
//
//    /**
//     * @param groupname the groupname to set
//     */
//    public void setGroupname(String groupname) {
//        this.groupname = groupname;
//    }
    /**
     * @return the dateend
     */
    public Date __getDateend() {
        return dateend;
    }

    /**
     * @param dateend the dateend to set
     */
    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    @Override
    public JComponent getView() {
        return new ItemPanel(Context.getItems(), __getInttype());
    }

    /**
     * @return the defaultaccountsids
     */
    public int __getDefaultaccountsids() {
        return defaultaccountsids;
    }

    /**
     * @param defaultaccountsids the defaultaccountsids to set
     */
    public void setDefaultaccountsids(int defaultaccountsids) {
        this.defaultaccountsids = defaultaccountsids;
    }

    /**
     * @return the description
     */
    public String __getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  <li>QUEUED = 0;
     *  <li>IN_PROGRESS= 1;
     *  <li>PAUSED = 2;
     *  <li>FINNISHED= 3;
     * @return the intstatus
     */
    public int __getIntstatus() {
        return intstatus;
    }

    /**
     *  <li>QUEUED = 0;
     *  <li>IN_PROGRESS= 1;
     *  <li>PAUSED = 2;
     *  <li>FINNISHED= 3;
     * @param intstatus the intstatus to set
     */
    public void setIntstatus(int intstatus) {
        this.intstatus = intstatus;
    }

    /**
     *  <li>TYPE_BILL = 0;
     *  <li>TYPE_ORDER = 1;
     *  <li>TYPE_OFFER = 2;
     * @return the inttype
     */
    public int __getInttype() {
        return inttype;
    }

    /**
     *  <li>TYPE_BILL = 0;
     *  <li>TYPE_ORDER = 1;
     *  <li>TYPE_OFFER = 2;
     * @param inttype the inttype to set
     */
    public void setInttype(int inttype) {
        this.inttype = inttype;
    }

    /**
     * @return the netvalue
     */
    public double __getNetvalue() {
        return netvalue;
    }

    /**
     * @param netvalue the netvalue to set
     */
    public void setNetvalue(double netvalue) {
        this.netvalue = netvalue;
    }

    @Override
    public mpv5.utils.images.MPIcon getIcon() {
        return new MPIcon("/mpv5/resources/images/22/kontact_mail.png");
    }

    /**
     * @return the formatHandler
     */
    public FormatHandler getFormatHandler() {
        if (formatHandler == null) {
            formatHandler = new FormatHandler(this);
        }
        return formatHandler;
    }

    @Override
    public void ensureUniqueness() {
        setCnumber(getFormatHandler().toString(getFormatHandler().getNextNumber()));
        setCName(__getCnumber());
    }

    /**
     * Fetches all related {@link Subitem}s to this {@link Item}
     * @return
     */
    public SubItem[] getSubitems() {
        ArrayList<DatabaseObject> data = new ArrayList<DatabaseObject>();
        try {
            data = DatabaseObject.getReferencedObjects(this, Context.getSubItem(), DatabaseObject.getObject(Context.getSubItem()));
        } catch (NodataFoundException ex) {
            Log.Debug(ex);
        }
        SubItem[] t = new SubItem[data.size()];
        for (int i = 0; i < data.size(); i++) {
            t[i] = (SubItem) data.get(i);
        }
        return data.toArray(new SubItem[]{});
    }

    /**
     * @return the cnumber
     */
    public String __getCnumber() {
        return cnumber;
    }

    /**
     * @param cnumber the cnumber to set
     */
    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }
}
