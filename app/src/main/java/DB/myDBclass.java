package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaruwat Sukhamjohn on 31/1/2558.
 */

public class myDBclass extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "propayment";

    // Table Transaction
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String TRAN_AMOUNT = "amount";
    public static final String TRAN_TYPE = "type";
    public static final String TRAN_CATE = "categoryName";
    public static final String TRAN_CATEICON = "categoryIcon";
    public static final String TRAN_DESCRIPTION = "description";
    public static final String TRAN_DAY = "day";
    public static final String TRAN_MONTH = "month";
    public static final String TRAN_YEAR = "year";

    // Table Max
    public static final String TABLE_MAX = "maximum";
    public static final String MAX_MAXIMUM = "max";
    public static final String MAX_DAY = "day";
    public static final String MAX_MONTH = "month";
    public static final String MAX_YEAR = "year";

    // Debt Table String
    public static final String TABLE_DEBT = "debt";
    public static final String DEBT_NAME = "debt_name";
    public static final String DEBT_AMOUNT = "debt_amount";
    public static final String DEBT_DAYSTART = "debt_daystart";
    public static final String DEBT_MONTHSTART = "debt_monthstart";
    public static final String DEBT_YEARSTART = "debt_yearstart";
    public static final String DEBT_DAYEND = "debt_dayend";
    public static final String DEBT_MONTHEND = "debt_monthend";
    public static final String DEBT_YEAREND = "debt_yearend";

    //Category Income Table
    public static final String TABLE_INCATE = "cateincome";
    public static final String INCATE_NAME = "name";
    public static final String INCATE_STATUS = "status";
    public static final String INCATE_ICON = "icon";

    //Category Expense Table
    public static final String TABLE_EXPENSECATE = "cateexpense";
    public static final String EXPENSECATE_NAME = "name";
    public static final String EXPENSECATE_STATUS = "status";
    public static final String EXPENSECATE_ICON = "icon";

    public myDBclass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Transactions = "CREATE TABLE " + TABLE_TRANSACTIONS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +
                TRAN_AMOUNT + " REAL," +
                TRAN_TYPE + " BOOLEAN," +
                TRAN_CATE + " TEXT (255)," +
                TRAN_CATEICON + " TEXT (255)," +
                TRAN_DESCRIPTION + " TEXT (255)," +
                TRAN_DAY + " TEXT(10)," +
                TRAN_MONTH + " TEXT(10)," +
                TRAN_YEAR + " TEXT(10)" +
                ")";

        String Create_Max = "CREATE TABLE " + TABLE_MAX + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +
                MAX_MAXIMUM + " REAL," +
                MAX_DAY + " TEXT(10)," +
                MAX_MONTH + " TEXT(10)," +
                MAX_YEAR + " TEXT(10)" +
                ")";

        db.execSQL("CREATE TABLE " + TABLE_DEBT
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DEBT_NAME + " TEXT, " + DEBT_AMOUNT + " REAL, "
                + DEBT_DAYSTART + " TEXT," + DEBT_MONTHSTART + " TEXT,"
                + DEBT_YEARSTART + " TEXT," + DEBT_DAYEND + " TEXT,"
                + DEBT_MONTHEND + " TEXT," + DEBT_YEAREND + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_INCATE
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + INCATE_NAME + " TEXT,"
                + INCATE_STATUS + " TEXT,"
                + INCATE_ICON + " TEXT);"
        );

        db.execSQL("CREATE TABLE " + TABLE_EXPENSECATE
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EXPENSECATE_NAME + " TEXT,"
                + EXPENSECATE_STATUS + " TEXT,"
                + EXPENSECATE_ICON + " TEXT);"
        );


        db.execSQL(Create_Transactions);
        db.execSQL(Create_Max);
        //db.execSQL(Create_Debt);


    }

    //Insert INCATE                                     true = can't delete,edit(status)
    public long INSERTINCATE(String strID, String strName, String status, String icon) {
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            ContentValues Val = new ContentValues();
            Val.put("_id", strID);
            Val.put(INCATE_NAME, strName);
            Val.put(INCATE_STATUS, status);
            Val.put(INCATE_ICON, icon);


            long rows = db.insert(TABLE_INCATE, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }

    }

    //Insert EXPENSECATE
    public long INSERTEXCATE(String strID, String strName, String status, String icon) {
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            ContentValues Val = new ContentValues();
            Val.put("_id", strID);
            Val.put(EXPENSECATE_NAME, strName);
            Val.put(EXPENSECATE_STATUS, status);
            Val.put(EXPENSECATE_ICON, icon);


            long rows = db.insert(TABLE_EXPENSECATE, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }

    }

    // Insert Data
    public long InsertData(String strID, Double strAmount, String strType
            , String strDes, String strcName, String strcIcon, String strDay, String strMonth, String strYear) {
        // TODO Auto-generated method stub

        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             *  for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "INSERT INTO " + TABLE_MEMBER
             + "(MemberID,Name,Tel) VALUES (?,?,?)";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMemberID);
             insertCmd.bindString(2, strName);
             insertCmd.bindString(3, strTel);
             return insertCmd.executeInsert();
             */

            ContentValues Val = new ContentValues();
            Val.put("_id", strID);
            Val.put(TRAN_AMOUNT, strAmount);
            Val.put(TRAN_TYPE, strType);
            Val.put(TRAN_DESCRIPTION, strDes);
            Val.put(TRAN_CATE, strcName);
            Val.put(TRAN_CATEICON, strcIcon);
            Val.put(TRAN_DAY, strDay);
            Val.put(TRAN_MONTH, strMonth);
            Val.put(TRAN_YEAR, strYear);


            long rows = db.insert(TABLE_TRANSACTIONS, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }

    }

    // Select Data
    public String[] SelectData(String strMemberID) {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_TRANSACTIONS, new String[]{"*"},
                    "MemberID=?",
                    new String[]{String.valueOf(strMemberID)}, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    /***
                     *  0 = MemberID
                     *  1 = Name
                     *  2 = Tel
                     */
                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Delete Data
    public long DeleteData(String strMemberID) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             * for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "DELETE FROM " + TABLE_MEMBER
             + " WHERE MemberID = ? ";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMemberID);

             return insertCmd.executeUpdateDelete();
             *
             */

            long rows = db.delete(TABLE_TRANSACTIONS, "_id = ?",
                    new String[]{String.valueOf(strMemberID)});

            db.close();
            return rows; // return rows delete.

        } catch (Exception e) {
            return -1;
        }

    }


    // Select All Data
    public Cursor SelectAllData() {
        // TODO Auto-generated method stub

        try {
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  ID As _id , * FROM " + TABLE_TRANSACTIONS;
            Cursor cursor = db.rawQuery(strSQL, null);

            return cursor;

        } catch (Exception e) {
            return null;
        }

    }

    // Select All Amount Data
    public String[][] SelectAllAmountData() {
        // TODO Auto-generated method stub

        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_TRANSACTIONS;
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    /***
                     *  [x][0] = MemberID
                     *  [x][1] = Name
                     *  [x][2] = Tel
                     */
                    int i = 0;
                    do {

                        arrData[i][0] = cursor.getString(1);
                        arrData[i][1] = cursor.getString(2);
                        arrData[i][2] = cursor.getString(6);
                        arrData[i][3] = cursor.getString(7);
                        arrData[i][4] = cursor.getString(8);

                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Delete All Data
    public void DeleteAllData() {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        db.delete(TABLE_TRANSACTIONS, null, null);
        db.close();
    }

    public long InsertDebtData(String strDebtID, String DebtName, double dAmount
            , String strDayStart, String strMonthStart, String strYearStart,
                               String strDayEnd, String strMonthEnd, String strYearEnd) {

        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             *  for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "INSERT INTO " + TABLE_MEMBER
             + "(MemberID,Name,Tel) VALUES (?,?,?)";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMemberID);
             insertCmd.bindString(2, strName);
             insertCmd.bindString(3, strTel);
             return insertCmd.executeInsert();
             */

            ContentValues Val = new ContentValues();
            /*Val.put("_id", strDebtID);
            Val.put("debt_name_c", DebtName);
            Val.put("debt_amountc", intAmount);
            Val.put("debt_daystartc", strDayStart);
            Val.put("debt_monthstartc", strMonthStart);
            Val.put("debt_yearstartc", strYearStart);
            Val.put("debt_dayendc", strDayEnd);
            Val.put("debt_monthendc", strMonthEnd);
            Val.put("debt_yearendc", strYearEnd);*/
            System.out.println(DebtName);

            db.execSQL("INSERT INTO " + TABLE_DEBT + " ("
                    + DEBT_NAME + ", " + DEBT_AMOUNT
                    + DEBT_DAYSTART + ", " + DEBT_MONTHSTART
                    + DEBT_YEARSTART + ", " + DEBT_DAYEND
                    + DEBT_MONTHEND + ", " + DEBT_YEAREND + ") VALUES ('" + DebtName
                    + "', '" + dAmount + "', '" + strDayStart
                    + "', '" + strMonthStart + "', '" + strYearStart +
                    "', '" + strDayEnd + "', '" + strMonthEnd +
                    "', '" + strYearEnd + "');");

            long rows = db.insert(TABLE_DEBT, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Select Day ()

    //Select MAXIMUM FROM TABLE MAX
    public String[] SelectMaxFromMonth(String month, String year) {

        SQLiteDatabase db;
        db = this.getReadableDatabase();

        String arrData[] = null;
        String strSQL = "SELECT " + myDBclass.MAX_MAXIMUM + ", " + myDBclass.MAX_MONTH + ", " + myDBclass.MAX_YEAR
                + " FROM " + myDBclass.TABLE_MAX + " WHERE " + myDBclass.MAX_MONTH + " =" + month + " AND " + myDBclass.MAX_YEAR
                + " = " + year;

        Cursor cursor = db.rawQuery(strSQL, null);

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    /***
                     *  0 = MAXIMUM
                     *  1 = MONTH
                     *  2 = YAER
                     */
                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Insert MAX TABLE
    public long InsertMaxTable(String strID, double dMax, String strDay, String strMonth, String strYear) {

        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             *  for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "INSERT INTO " + TABLE_MEMBER
             + "(MemberID,Name,Tel) VALUES (?,?,?)";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMemberID);
             insertCmd.bindString(2, strName);
             insertCmd.bindString(3, strTel);
             return insertCmd.executeInsert();
             */

            ContentValues Val = new ContentValues();
            Val.put("_id", strID);
            Val.put(MAX_MAXIMUM, dMax);
            Val.put(MAX_DAY, strDay);
            Val.put(MAX_MONTH, strMonth);
            Val.put(MAX_YEAR, strYear);

            long rows = db.insert(TABLE_MAX, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }

    }

    // /Check Table Not Null
    public int CheckTableNotNull(String strTable) {
        SQLiteDatabase database;
        database = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + strTable;
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);

        // return Minus Number  = Table Null,  Number > 0 = Table have data
        return icount;
    }

    public void deleteExpenseCategory(String id) {
        SQLiteDatabase sql = this.getWritableDatabase();
        sql.delete(TABLE_EXPENSECATE, "_id" + " = " + id, null);
        sql.close();
    }
}
