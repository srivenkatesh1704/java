import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EmployeeWage implements IComputeEmpWage {
                public static final int IS_FULL_TIME=1;
                public static final int IS_PART_TIME=2;

                private int numofCompany=0;
                private LinkedList<CompanyEmpWage> companyEmpWageList;
                private Map<String,CompanyEmpWage> companyToEmpWageMap;

                public EmployeeWage() {
                        companyEmpWageList = new LinkedList<>();
                        companyToEmpWageMap = new HashMap<>();
                }
                public void addCompanyEmpWage(String company,int empRateperHour,int workingDays,int maxHours) {
                        CompanyEmpWage companyEmpWage = new CompanyEmpWage(company,empRateperHour,workingDays,maxHours);
                        companyEmpWageList.add(companyEmpWage);
                        companyToEmpWageMap.put(company, companyEmpWage);
                }


                public void computeEmpWage(){
                        for(int i=0;i<companyEmpWageList.size();i++) {
                                CompanyEmpWage companyEmpWage = companyEmpWageList.get(i);
                                companyEmpWage.setTotalEmpWage(this.computeEmpWages(companyEmpWage));
                                System.out.println(companyEmpWage);

                        }
                        }
                public int computeEmpWages(CompanyEmpWage companyEmpWage) {
                        int emphrs = 0, totalworkingdays = 0, totalEmphrs = 0;
                        while (totalEmphrs < companyEmpWage.maxHours && totalworkingdays < companyEmpWage.workingDays)
                        {
                                totalworkingdays++;
                                int random = (int) Math.floor(Math.random() * 10) % 3;
                                switch (random) {

                                case 1:
                                        emphrs = 8;
                                        break;

                                case 2:
                                        emphrs = 4;
                                        break;

                                default:
                                        emphrs = 0;// not present

                                }
                                totalEmphrs += emphrs;
                        }
                        return totalEmphrs*companyEmpWage.empRateperHour;

        }


        @Override
        public int getTotalWage(String company) {
                return companyToEmpWageMap.get(company).totalEmpWage;
        }


        public static void main(String[] args) {
                // TODO Auto-generated method stub
                System.out.println("=========Welcome to Employee Wage Computation=======");
                IComputeEmpWage empWageBuilder = new EmployeeWage();
                empWageBuilder.addCompanyEmpWage("facebook",20,200,100);
                empWageBuilder.addCompanyEmpWage("apple",10,400,200);
                empWageBuilder.computeEmpWage();
                System.out.println("Total Wage for facebook company is :"+empWageBuilder.getTotalWage("facebook"));
        }

}
class CompanyEmpWage {
        public final String company;
        public final int empRateperHour;
        public final int workingDays;
        public final int maxHours;
        public int totalEmpWage;

        public CompanyEmpWage(String company,int empRatePerHour,int workingDays,int maxHours) {
                this.company=company;
                this.empRateperHour=empRatePerHour;
                this.workingDays=workingDays;
                this.maxHours=maxHours;
        }
        public void setTotalEmpWage(int totalEmpWage) {
                this.totalEmpWage=totalEmpWage;
        }
        @Override
        public String toString() {
                return "Total Emp Wage for Company "+company+" is :"+totalEmpWage;
        }

}
interface IComputeEmpWage {
        public void addCompanyEmpWage(String company,int empRateperHour,int workingDays,int maxHours);
        public void computeEmpWage();
        public int getTotalWage(String company);
        }
