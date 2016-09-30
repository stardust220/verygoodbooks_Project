package vgb.domain;

import java.text.*;
import java.util.*;

public class Customer{
    public static final char MALE = 'M';
    public static final char FEMALE = 'F';

    private String id; //ROC ID, Pkey
    private String password; //6~20
    private String name = ""; //2~20
    private String email; //regular exp: 
    /**
     * M:male, F:female
     */
    private char gender;

    private Date birthday; //age 12~
    private String phone; //0~20
    private String address; //0~100
    private boolean married;
    private BloodType bloodType;

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setBloodType(String bloodType) {
        if(bloodType!=null && (bloodType=bloodType.trim()).length()>0){            
            this.setBloodType(BloodType.valueOf(bloodType));            
        }else{
            this.bloodType = null;
        }
    }

    //給boundary(view, controller)使用的建構子
    public Customer() {
        System.out.println("Customer created...");
    }

    //給DAO使用的建構子
    public Customer(String id, String pwd, String name) {
        this.id = id;
        this.password = pwd;
        this.name = name;
    }

    //給DAO使用的建構子
    public Customer(String id, String password, String name,
            String email, char gender) {
        this(id, password, name);
        this.email = email;
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date bDate) throws VGBException {
        if ((bDate == null)
                || (bDate != null && bDate.before(new Date()))) {
            birthday = bDate;
        } else {
            System.err.println("出生日期必須小於現在!");
            throw new VGBException("出生日期必須小於現在!");
        }
    }

    public void setBirthday(String bDate) throws VGBException { //yyyy-MM-dd
        if (bDate != null && (bDate = bDate.trim()).length() > 0) {
            bDate = bDate.replace('/', '-');
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(bDate); //checked exception: ParseException            
                //System.out.println("date = " + date);
                this.setBirthday(date);
            } catch (ParseException ex) {
                System.err.println("客戶生日日期格式不正確: " + bDate);
                throw new VGBException("客戶生日日期格式不正確: " + bDate);
            }
//            String[] data = bDate.split("-");
//            if(data.length==3){
//                int year = Integer.parseInt(data[0]);
//                int month = Integer.parseInt(data[1]);
//                int day = Integer.parseInt(data[2]);        
//                this.setBirthday(year, month, day);                                
//            }else{
//                System.err.println("生日字串格式不正確");
//            }
        } else {
            birthday = null;
        }
    }

    public void setBirthday(int year, int month, int day) throws VGBException {
        Date date = new GregorianCalendar(year, month - 1, day).getTime();
        this.setBirthday(date);
    }

    public int getAge() {
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("thisYear = " + thisYear);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getBirthday());

        int birthYear = calendar.get(Calendar.YEAR);

        int age = thisYear - birthYear;

        System.out.println("c.age=" + age);
        return age;
    }

    public static boolean checkId(String id) {
        //1. 檢查格式符合regular exp.: [A-Z][12][0-9]{8}
        if (id != null && id.matches("[A-Z][12]\\d{8}")) {
            String id9 = id.substring(0, 9);
            char rtn = generateIdLastChar(id9);
            char lastChar = id.charAt(9);
            return lastChar == rtn;
        } else {
            System.out.println("身分證號格式不正確");
            return false;
        }
    }

    static char generateIdLastChar(String id9) {
        //1. 檢查格式符合regular exp.: [A-Z][12][0-9]{8}
        if (id9 != null && id9.matches("[A-Z][12]\\d{7}")) {
            //2. 將第一個英文字母轉換為對應的數字
            char firstChar = id9.charAt(0);
            int firstNumber = 0;
            if (firstChar >= 'A' && firstChar <= 'H') {//A~H: 10~17
                firstNumber = firstChar - 'A' + 10;
            } else if (firstChar >= 'J' && firstChar <= 'N') {//J~N: 18~22
                firstNumber = firstChar - 'J' + 18;
            } else if (firstChar >= 'P' && firstChar <= 'V') {//P~V: 23~29
                firstNumber = firstChar - 'P' + 23;
            } else {
                switch (firstChar) {
                    default:
//                        System.out.println("身分證號首碼無法轉換: " + firstChar));
                        assert false : "身分證號首碼無法轉換: " + firstChar;
                        return 'E';
                    case 'I':
                        firstNumber = 34;
                        break;
                    case 'O':
                        firstNumber = 35;
                        break;
                    case 'W':
                        firstNumber = 32;
                        break;
                    case 'X':
                        firstNumber = 30;
                        break;
                    case 'Y':
                        firstNumber = 31;
                        break;
                    case 'Z':
                        firstNumber = 33;
                }
            }
            assert firstNumber >= 10 && firstNumber <= 35 : ("身分證號首碼轉換錯誤: " + firstChar + "->" + firstNumber);

            //3. 將步驟2產生的數字與身分證號後9碼數字依規則計算得到一個整數        
            int sum = (firstNumber / 10) + (firstNumber % 10 * 9);
            for (int i = 1; i < 9; i++) {
                sum = sum + (id9.charAt(i) - '0') * (9 - i);
            }

            //4. 將步驟3產生的整數%10，再用10-mod10的餘數即為結果字元
            int mod10 = sum % 10;
            char lastChar = (char) ((mod10 == 0 ? 0 : (10 - mod10)) + '0');
            assert (lastChar >= '0' && lastChar <= '9') : "身分證號尾碼轉換錯誤- [" + lastChar + ']';
            return lastChar;
        } else {
            System.out.println("身分證號前九碼格式不正確");
            throw new java.lang.IllegalArgumentException("身分證號前九碼格式不正確");
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) throws VGBException {
        if (id != null && checkId(id = id.trim())) {
            this.id = id;
        } else {
            System.err.println("身分證號不正確");
            throw new VGBException("身分證號不正確");
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) throws VGBException {
        if (password != null
                && (password = password.trim()).length() >= 6
                && password.length() <= 20) {
            this.password = password;
        } else {
            System.err.println("密碼長度必須在6~20之間");
            throw new VGBException("密碼長度必須在6~20之間");
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) throws VGBException {
        if (name != null && (name = name.trim()).length() > 0) {
            this.name = name;
        } else {
            System.err.println("姓名為必要欄位");
            throw new VGBException("姓名為必要欄位");
        }
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) throws VGBException {
        if (email != null
                && (email = email.trim()).matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            this.email = email;
        } else {
            System.err.println("email格式不正確");
            throw new VGBException("email格式不正確");
        }
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(char gender) throws VGBException {
        if (gender == MALE || gender == FEMALE) {
            this.gender = gender;
        } else {
            System.err.println("性別資料不正確");
            throw new VGBException("性別資料不正確");
        }
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = (phone == null ? null : phone.trim());
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = (address == null ? null : address.trim());
    }

    /**
     * @return the married
     */
    public boolean isMarried() {
        return married;
    }

    /**
     * @param married the married to set
     */
    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return super.toString() + "{" + "id=" + id
                + ", password=" + password
                + ", name=" + name + ", email="
                + email + ", gender=" + gender + ",\n birthday="
                + birthday + ", phone=" + phone + ", address="
                + address + ", married=" + married + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Customer.generateIdLastChar("A12345677"));
    }

}
