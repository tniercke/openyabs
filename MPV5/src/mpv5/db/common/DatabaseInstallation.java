  /*
 *  This file is part of MP.
 *
 *  MP is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  MP is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.db.common;

import mpv5.globals.Messages;

/**
 * 
 */
public class DatabaseInstallation {


     /**
       * This contains the database structure for mpv5
       *
       * As SQL.Views are currently not updateable from DERBY, i use two nearly identical tables here, to store user informations.
       * First one holds a users default data, where the second table holds additional address info.
       *
     */
    public final static String[] DERBY_STRUCTURE = new String[]{

//sub tables
"CREATE TABLE groups (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) UNIQUE NOT NULL,description VARCHAR(750) DEFAULT NULL,defaults VARCHAR(250) DEFAULT NULL,groupsids BIGINT DEFAULT 0,dateadded DATE NOT NULL,reserve1 VARCHAR(500) default NULL,intaddedby BIGINT DEFAULT 0, reserve2 VARCHAR(500) default NULL, PRIMARY KEY  (ids))",
"CREATE TABLE tax (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250), taxvalue DOUBLE DEFAULT 0,identifier VARCHAR(250) UNIQUE NOT NULL,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,PRIMARY KEY  (ids))",
"CREATE TABLE history (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250)  NOT NULL, username VARCHAR(250) NOT NULL,dbidentity VARCHAR(25)  NOT NULL, INTitem SMALLINT NOT NULL,groupsids BIGINT DEFAULT 0, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,reserve1 VARCHAR(500) default NULL, reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE countries (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250)  UNIQUE NOT NULL, iso SMALLINT  UNIQUE NOT NULL,groupsids BIGINT DEFAULT 0,reserve1 VARCHAR(500) default NULL, reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE searchindex (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),dbidentity VARCHAR(25) NOT NULL, rowID BIGINT NOT NULL,text VARCHAR(5000) default NULL)",

//Main tables, must have ids, cname, groupsids, dateadded, intaddedby, invisible
"CREATE TABLE contacts (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cnumber VARCHAR(250),taxnumber VARCHAR(250),title VARCHAR(250) default NULL,groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,country VARCHAR(50) default NULL, prename VARCHAR(250) default NULL, cname VARCHAR(250) default NULL, street VARCHAR(250) default NULL,zip VARCHAR(50) default NULL,city VARCHAR(300) default NULL, mainphone VARCHAR(250) default NULL,fax VARCHAR(250) default NULL,mobilephone VARCHAR(250) default NULL,workphone VARCHAR(250) default NULL,mailaddress VARCHAR(350) default NULL,company VARCHAR(250) DEFAULT NULL, department VARCHAR(250) DEFAULT NULL,website VARCHAR(350) default NULL,notes VARCHAR(10000),dateadded DATE NOT NULL,isactive SMALLINT DEFAULT 0,iscustomer SMALLINT DEFAULT 0,ismanufacturer SMALLINT DEFAULT 0,issupplier SMALLINT DEFAULT 0,iscompany SMALLINT DEFAULT 0,ismale SMALLINT DEFAULT 0,isenabled SMALLINT DEFAULT 1,intaddedby BIGINT DEFAULT 0, invisible SMALLINT DEFAULT 0, reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE users (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) UNIQUE NOT NULL, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1, intdefaultaccount BIGINT DEFAULT 1,fullname VARCHAR(250) NOT NULL, password VARCHAR(250) NOT NULL,laf VARCHAR(50) default NULL, locale VARCHAR(50) default NULL, defcountry VARCHAR(50) default NULL, mail VARCHAR(50) default NULL, language VARCHAR(50) default NULL, inthighestright SMALLINT DEFAULT 3,isenabled SMALLINT DEFAULT 1,isrgrouped SMALLINT DEFAULT 0,isloggedin SMALLINT DEFAULT 0,datelastlog DATE default NULL, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE files (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(25) UNIQUE NOT NULL, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,dateadded DATE NOT NULL,data BLOB(5M) NOT NULL,filesize BIGINT NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,PRIMARY KEY  (ids))",
"CREATE TABLE languages(IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) UNIQUE NOT NULL, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,longname VARCHAR(250) UNIQUE NOT NULL, filename VARCHAR(25) NOT NULL REFERENCES files(cname) ON DELETE CASCADE,dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE favourites (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) NOT NULL, usersids BIGINT REFERENCES users (ids)  ON DELETE CASCADE,groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,itemsids BIGINT NOT NULL,dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL, reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE accounts(IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), intaccountclass SMALLINT DEFAULT 0, cname VARCHAR(250) NOT NULL, description VARCHAR(250) NOT NULL, taxvalue DOUBLE NOT NULL DEFAULT 0, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0, intparentaccount BIGINT DEFAULT 0, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1, invisible SMALLINT DEFAULT 0, intaccounttype SMALLINT NOT NULL, PRIMARY KEY  (ids))",
"CREATE TABLE items (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), cname VARCHAR(250) UNIQUE NOT NULL, description VARCHAR(2500) DEFAULT NULL, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1, defaultaccountsids BIGINT  REFERENCES accounts(ids) DEFAULT 1,contactsids BIGINT REFERENCES contacts(ids)  ON DELETE CASCADE, netvalue DOUBLE DEFAULT 0,taxvalue DOUBLE DEFAULT 0, datetodo DATE DEFAULT NULL, dateend DATE DEFAULT NULL, intreminders INTEGER DEFAULT 0, inttype SMALLINT DEFAULT 0, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,intstatus SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL, reserve2 VARCHAR(500) default NULL, PRIMARY KEY  (ids))",
"CREATE TABLE itemslists (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), description VARCHAR(2500), cname VARCHAR(250), groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1, itemsids BIGINT  REFERENCES items(ids) ON DELETE RESTRICT,dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL, reserve2 VARCHAR(500) default NULL, PRIMARY KEY  (ids))",
"CREATE TABLE subitems (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), cname VARCHAR(2500) default NULL,itemsids BIGINT REFERENCES items(ids)  ON DELETE CASCADE, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,originalproductsids BIGINT DEFAULT NULL, countvalue DOUBLE DEFAULT 0 NOT NULL, quantityvalue DOUBLE DEFAULT 0 NOT NULL, measure VARCHAR(250) NOT NULL,description VARCHAR(1000) default NULL,  value DOUBLE DEFAULT 0 NOT NULL, taxpercentvalue DOUBLE DEFAULT 0 NOT NULL,datedelivery DATE DEFAULT NULL, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE schedule (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) NOT NULL, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,usersids BIGINT REFERENCES users (ids)  ON DELETE CASCADE,itemsids BIGINT REFERENCES items (ids)  ON DELETE CASCADE,nextdate DATE NOT NULL, intervalmonth SMALLINT NOT NULL, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL, reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE products (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), cname VARCHAR(500) NOT NULL, cnumber VARCHAR(150) , description VARCHAR(500), pricenetvalue DOUBLE DEFAULT 0, buypricevalue DOUBLE DEFAULT 0, taxids BIGINT REFERENCES tax(ids) ON DELETE CASCADE, manufacturer BIGINT REFERENCES contacts(ids)  ON DELETE CASCADE DEFAULT NULL,supplier BIGINT REFERENCES contacts(ids)  ON DELETE CASCADE DEFAULT NULL, groupsids  BIGINT  REFERENCES groups(ids) DEFAULT 1,url VARCHAR(250) default NULL,ean VARCHAR(25) default null, reference VARCHAR(50) default null,dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0,invisible SMALLINT DEFAULT 0,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE userproperties(IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) NOT NULL, value VARCHAR(250) NOT NULL, usersids BIGINT NOT NULL, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,invisible SMALLINT DEFAULT 0, PRIMARY KEY  (ids))",
"CREATE TABLE messages(IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(550) NOT NULL, dateadded DATE NOT NULL,intaddedby BIGINT DEFAULT 0, groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,invisible SMALLINT DEFAULT 0, PRIMARY KEY  (ids))",


//sub tables #2
"CREATE TABLE tablelock (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250), rowID BIGINT NOT NULL, usersids BIGINT REFERENCES users(ids)  ON DELETE CASCADE,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids), CONSTRAINT one_lock UNIQUE(cname, rowid))",

"CREATE TABLE itemstoaccounts (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), itemsids BIGINT NOT NULL REFERENCES items(ids) ON DELETE CASCADE, accountsids BIGINT REFERENCES accounts(ids)  ON DELETE CASCADE,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE messagestoitems (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), itemsids BIGINT NOT NULL REFERENCES items(ids) ON DELETE CASCADE, messagesids BIGINT REFERENCES messages(ids)  ON DELETE CASCADE,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE trashbin (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(500), rowID BIGINT NOT NULL, description VARCHAR(2500), deleteme SMALLINT DEFAULT 1, reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",
"CREATE TABLE filestocontacts(IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) NOT NULL, description VARCHAR(550) DEFAULT NULL, contactsids BIGINT NOT NULL  REFERENCES contacts(ids) ON DELETE CASCADE,filename VARCHAR(25) NOT NULL REFERENCES files(cname) ON DELETE CASCADE,intaddedby BIGINT DEFAULT 0,dateadded DATE NOT NULL,groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,PRIMARY KEY  (ids))",
"CREATE TABLE plugins(IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) NOT NULL, description VARCHAR(550) DEFAULT NULL,filename VARCHAR(25) NOT NULL REFERENCES files(cname) ON DELETE CASCADE,intaddedby BIGINT DEFAULT 0,dateadded DATE NOT NULL,groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,PRIMARY KEY  (ids))",
"CREATE TABLE pluginstousers(IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),cname VARCHAR(250) NOT NULL, usersids BIGINT NOT NULL, pluginsids BIGINT NOT NULL REFERENCES plugins(ids) ON DELETE CASCADE,intaddedby BIGINT DEFAULT 0,dateadded DATE NOT NULL,groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,PRIMARY KEY  (ids))",
"CREATE TABLE addresses (IDS BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),groupsids BIGINT  REFERENCES groups(ids) DEFAULT 1,contactsids BIGINT REFERENCES contacts(ids),title VARCHAR(250) default NULL, taxnumber VARCHAR(250),prename VARCHAR(250) default NULL, cname VARCHAR(250) default NULL, street VARCHAR(250) default NULL,zip VARCHAR(50) default NULL,city VARCHAR(300) default NULL, company VARCHAR(250) DEFAULT NULL, department VARCHAR(250) DEFAULT NULL,country VARCHAR(50) default NULL, ismale SMALLINT DEFAULT 0,intaddedby BIGINT DEFAULT 0,dateadded DATE DEFAULT NULL,reserve1 VARCHAR(500) default NULL,reserve2 VARCHAR(500) default NULL,PRIMARY KEY  (ids))",

//Trigger
"CREATE TRIGGER contacts_indexer1 AFTER INSERT ON contacts REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('contacts',newdata.ids,newdata.cnumber||' '||newdata.taxnumber||' '||newdata.title||' '||newdata.country||' '|| newdata.prename||' '|| newdata.cname||' '|| newdata.street||' '||newdata.zip||' '|| newdata.city ||' '||newdata.mainphone||' '||newdata.fax||' '||newdata.mobilephone||' '||newdata.workphone||' '||newdata.mailaddress||' '||newdata.company||' '|| newdata.department||' '||newdata.website||' '||newdata.notes)",
"CREATE TRIGGER contacts_indexer2 AFTER UPDATE ON contacts REFERENCING NEW AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'contacts' AND  rowid = newdata.ids",
"CREATE TRIGGER contacts_indexer3 AFTER UPDATE ON contacts REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('contacts',newdata.ids,newdata.cnumber||' '||newdata.taxnumber||' '||newdata.title||' '||newdata.country||' '|| newdata.prename||' '|| newdata.cname||' '|| newdata.street||' '||newdata.zip||' '|| newdata.city ||' '||newdata.mainphone||' '||newdata.fax||' '||newdata.mobilephone||' '||newdata.workphone||' '||newdata.mailaddress||' '||newdata.company||' '|| newdata.department||' '||newdata.website||' '||newdata.notes)",
"CREATE TRIGGER contacts_indexer4 AFTER DELETE ON contacts REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'contacts' AND  rowid = newdata.ids",
"CREATE TRIGGER contacts_trash1   AFTER UPDATE ON contacts REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO trashbin (deleteme, cname, rowid, description) VALUES (newdata.invisible,'contacts',newdata.ids,newdata.cnumber||' ('|| newdata.cname||')')",
"CREATE TRIGGER contacts_trash2 AFTER DELETE ON contacts REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM trashbin WHERE cname = 'contacts' AND  rowid = newdata.ids",


"CREATE TRIGGER filestocontacts_indexer1 AFTER INSERT ON filestocontacts REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('filestocontacts',newdata.ids,newdata.cname||' '||newdata.description||' '||newdata.filename)",
"CREATE TRIGGER filestocontacts_indexer2 AFTER UPDATE ON filestocontacts REFERENCING NEW AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'filestocontacts' AND  rowid = newdata.ids",
"CREATE TRIGGER filestocontacts_indexer3 AFTER UPDATE ON filestocontacts REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('filestocontacts',newdata.ids,newdata.cname||' '||newdata.description||' '||newdata.filename)",
"CREATE TRIGGER filestocontacts_indexer4 AFTER DELETE ON filestocontacts REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'filestocontacts' AND  rowid = newdata.ids",
"CREATE TRIGGER groups_indexer1 AFTER INSERT ON groups REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('groups',newdata.ids,newdata.cname||' '||newdata.description||' '||newdata.dateadded)",
"CREATE TRIGGER groups_indexer2 AFTER UPDATE ON groups REFERENCING NEW AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'groups' AND  rowid = newdata.ids",
"CREATE TRIGGER groups_indexer3 AFTER UPDATE ON groups REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('groups',newdata.ids,newdata.cname||' '||newdata.description||' '||newdata.dateadded)",
"CREATE TRIGGER groups_indexer4 AFTER DELETE ON groups REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'groups' AND  rowid = newdata.ids",
"CREATE TRIGGER items_indexer1 AFTER INSERT ON items REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('items',newdata.ids,newdata.cname||' '||newdata.dateadded)",
"CREATE TRIGGER items_indexer2 AFTER UPDATE ON items REFERENCING NEW AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'items' AND  rowid = newdata.ids",
"CREATE TRIGGER items_indexer3 AFTER UPDATE ON items REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('items',newdata.ids,newdata.cname||' '||newdata.dateadded)",
"CREATE TRIGGER items_indexer4 AFTER DELETE ON items REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'items' AND  rowid = newdata.ids",

"CREATE TRIGGER items_trash2 AFTER DELETE ON items REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM trashbin WHERE cname = 'items' AND  rowid = newdata.ids",
"CREATE TRIGGER items_trash1 AFTER UPDATE ON items REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO trashbin (deleteme, cname, rowid, description) VALUES (newdata.invisible,'items',newdata.ids,newdata.cname)",


"CREATE TRIGGER subitems_indexer1 AFTER INSERT ON subitems REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('subitems',newdata.ids,newdata.cname||' '||newdata.description||' '||newdata.dateadded)",
"CREATE TRIGGER subitems_indexer2 AFTER UPDATE ON subitems REFERENCING NEW AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'subitems' AND  rowid = newdata.ids",
"CREATE TRIGGER subitems_indexer3 AFTER UPDATE ON subitems REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('subitems',newdata.ids,newdata.cname||' '||newdata.description||' '||newdata.dateadded)",
"CREATE TRIGGER subitems_indexer4 AFTER DELETE ON subitems REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'subitems' AND  rowid = newdata.ids",
"CREATE TRIGGER products_indexer1 AFTER INSERT ON products REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('products',newdata.ids,newdata.cname||' '||newdata.cnumber||' '||newdata.description||' '||newdata.dateadded)",
"CREATE TRIGGER products_indexer2 AFTER UPDATE ON products REFERENCING NEW AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'products' AND  rowid = newdata.ids",
"CREATE TRIGGER products_indexer3 AFTER UPDATE ON products REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO searchindex (dbidentity, rowid, text) VALUES ('products',newdata.ids,newdata.cname||' '||newdata.cnumber||' '||newdata.description||' '||newdata.dateadded)",
"CREATE TRIGGER products_indexer4 AFTER DELETE ON products REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM searchindex WHERE dbidentity = 'products' AND  rowid = newdata.ids",


"CREATE TRIGGER products_trash1 AFTER UPDATE ON products REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO trashbin (deleteme, cname, rowid, description) VALUES (newdata.invisible, 'products',newdata.ids,newdata.cnumber||' ('|| newdata.cname||')')",
"CREATE TRIGGER messages_trash1 AFTER UPDATE ON messages REFERENCING NEW AS newdata FOR EACH ROW INSERT INTO trashbin (deleteme, cname, rowid, description) VALUES (newdata.invisible, 'messages',newdata.ids,' ('|| newdata.cname||')')",
"CREATE TRIGGER messages_trash2 AFTER DELETE ON messages REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM trashbin WHERE cname = 'messages' AND  rowid = newdata.ids",
"CREATE TRIGGER products_trash2 AFTER DELETE ON products REFERENCING OLD AS newdata FOR EACH ROW DELETE FROM trashbin WHERE cname = 'products' AND  rowid = newdata.ids",


"CREATE TRIGGER thrash_handler1 AFTER INSERT ON trashbin FOR EACH STATEMENT DELETE FROM trashbin WHERE deleteme = 0",
"CREATE TRIGGER thrash_handler2 AFTER INSERT ON trashbin FOR EACH STATEMENT DELETE FROM trashbin WHERE ids IN (SELECT ids FROM trashbin WHERE EXISTS( SELECT ids FROM trashbin AS tmptable WHERE trashbin.cname = tmptable.cname AND trashbin.rowid = tmptable.rowid HAVING trashbin.ids < MAX(tmptable.ids) ) )",

"INSERT INTO tax(cname, dateadded, identifier) VALUES ('Default', '2009-04-03 09:31:33', 'Default 0%')",
"INSERT INTO tax(cname, dateadded, identifier, taxvalue) VALUES ('Default', '2009-04-03 09:31:33', 'Default 19%', 19.0)",
"INSERT INTO tax(cname, dateadded, identifier, taxvalue) VALUES ('Default', '2009-04-03 09:31:33', 'Default 7%', 7.0)",

"INSERT INTO groups (cname,description, dateadded) VALUES ('"+Messages.GROUPNAMES+"','This group holds all yet ungrouped items.', '2009-04-03 09:31:33')",
"INSERT INTO groups (cname,description, dateadded) VALUES ('ungrouped','This group holds all yet ungrouped items.', '2009-04-03 09:31:33')",
"INSERT INTO accounts (cname,description, dateadded, taxvalue, intaccounttype) VALUES ('"+Messages.ACCOUNTNAMES+"','This account holds all yet ungrouped items.', '2009-04-03 09:31:33', 0.0, 0)",
"INSERT INTO users (fullname,password,cname,laf,locale,mail,language,inthighestright,datelastlog,isenabled, dateadded ) VALUES ('Administrator','5f4dcc3b5aa765d61d8327deb882cf99','admin','de.muntjak.tinylookandfeel.TinyLookAndFeel','en_GB','','buildin_en',0,'2009-04-03 09:31:33',1,'2009-04-03 09:31:33')"

    };





    private String[] MYSQL_STRUCTURE;
    private String[] CUSTOM_STRUCTURE;

    public String[] getStructure() {
        if (ConnectionTypeHandler.getDriverType() == ConnectionTypeHandler.DERBY) {
            return DERBY_STRUCTURE;
        } else if (ConnectionTypeHandler.getDriverType() == ConnectionTypeHandler.MYSQL) {
            return MYSQL_STRUCTURE;
        } else {
            return CUSTOM_STRUCTURE;
        }
    }

    /**
     * @param CUSTOM_STRUCTURE the CUSTOM_STRUCTURE SQL commands to set
     */
    public void setCUSTOM(String[] CUSTOM_STRUCTURE) {
        this.CUSTOM_STRUCTURE = CUSTOM_STRUCTURE;
    }
}
