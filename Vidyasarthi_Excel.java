package Excel_Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Vidyasarthi_Excel {

	public static void main(String[] args) throws IOException, InterruptedException {
          
		    ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");
	        WebDriver driver = new ChromeDriver(options);
	        driver.manage().window().maximize();

	        
	                
	        File src = new File("C:\\Users\\Shravani\\Desktop\\Excel_data\\Vidyasarthi_Excel.xlsx");
	        FileInputStream fis = new FileInputStream(src);
	        
	        XSSFWorkbook xsf = new XSSFWorkbook(fis);
	        XSSFSheet sheet = xsf.getSheetAt(0);	
	        
	        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
	            XSSFRow row = sheet.getRow(rowNum);
	            
	            String Login  = row.getCell(0).getStringCellValue();
	            String Password = row.getCell(1).getStringCellValue(); 
	            String Fullname = row.getCell(2).getStringCellValue(); 
	            String Gender = row.getCell(3).getStringCellValue(); 
	            String caste = row.getCell(4).getStringCellValue(); 
	            String title = row.getCell(5).getStringCellValue(); 
	            String Fname = row.getCell(6).getStringCellValue(); 
	            String Mname = row.getCell(7).getStringCellValue(); 
	            String Lname = row.getCell(8).getStringCellValue(); 
	            String Relationship = row.getCell(9).getStringCellValue(); 
	            String Occupation = row.getCell(10).getStringCellValue(); 
	            long contactNumber=(long) row.getCell(11).getNumericCellValue();
	            long familyincome=(long) row.getCell(12).getNumericCellValue();
	            String address = row.getCell(13).getStringCellValue(); 
	            long pin =(long) row.getCell(14).getNumericCellValue();
	            long accountNumber =(long) row.getCell(15).getNumericCellValue();
	            long reEnter =(long) row.getCell(16).getNumericCellValue();
	            String  ifsc_Code = row.getCell(17).getStringCellValue(); 
	            String Bname  = row.getCell(18).getStringCellValue(); 
	            String Courselevel  = row.getCell(19).getStringCellValue(); 
	            String CourseName  = row.getCell(20).getStringCellValue(); 
	            String InstituteName  = row.getCell(21).getStringCellValue(); 
	            long TuitionFees =(long) row.getCell(22).getNumericCellValue();
	            long NonTuitions =(long) row.getCell(23).getNumericCellValue();
//10 School:	            
	            String NOInstitute  = row.getCell(24).getStringCellValue(); 
	            String State  = row.getCell(25).getStringCellValue(); 
	            long MarksObtain =(long) row.getCell(26).getNumericCellValue();
	            long OutOfTotal =(long) row.getCell(27).getNumericCellValue();
//12 School : 
	            String SchoolName  = row.getCell(28).getStringCellValue(); 
	            String State1  = row.getCell(29).getStringCellValue(); 
	            long MObtain =(long) row.getCell(30).getNumericCellValue();
	            long Tmark =(long) row.getCell(31).getNumericCellValue();
	     
//Diploma
	            String DCollege = row.getCell(32).getStringCellValue();   
	    //        String State2  = row.getCell(33).getStringCellValue(); 
	            long DMObtain =(long) row.getCell(33).getNumericCellValue(); 
	            long DTMarks =(long) row.getCell(34).getNumericCellValue(); 
//Graduation :
	            
	            String GCollege = row.getCell(35).getStringCellValue();    
	            long GMObtain =(long) row.getCell(36).getNumericCellValue(); 
	            long GTMarks =(long) row.getCell(37).getNumericCellValue(); 
//Current Year :
	            String CYear = row.getCell(38).getStringCellValue();  
	            long CYmarks =(long) row.getCell(39).getNumericCellValue(); 
	            long CYCGPA =(long) row.getCell(40).getNumericCellValue(); 
	            
	            
	            
	            
	            // Open URL Of "Vidyasarthi"
		        driver.get("https://www.vidyasaarathi.co.in/Vidyasaarathi/index");

		         // Handle the PopUp
		        driver.findElement(By.xpath("//*[@id=\"modalSocial\"]/div/div/div[3]/a")).click();

		       //  Login to Vidyasarthi
		        driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[8]/a")).click();

		        // Switch to the new page
		        String mainWindowHandle = driver.getWindowHandle();
		        for (String handle : driver.getWindowHandles()) {
		            if (!handle.equals(mainWindowHandle)) {
		                driver.switchTo().window(handle);
		                break;
		            }
		        }
	        
		        WebElement emailInput = driver.findElement(By.id("username"));
	            emailInput.sendKeys(Login );

	            WebElement passwordInput = driver.findElement(By.name("password"));
	            passwordInput.sendKeys(Password);
	        
	            
	            Thread.sleep(13000);
	         // Submit the login form
	            WebElement submitButton = driver.findElement(By.id("login"));
	            submitButton.click();
	      //      Thread.sleep(3000);
	            //close button popup
	            driver.findElement(By.xpath("//*[@id=\"modalonlinetest\"]/div/div/div[2]/a")).click();
	            
	          
	            
	             //click on profile button 
	             driver.findElement(By.xpath("//*[@id=\"studentMenu\"]/li[3]/a")).click();
	             Thread.sleep(2000);
	          
//Personal Details : 
	             
	             //Full name :
	             
	               WebElement fullNameField = driver.findElement(By.id("fullName"));
		           fullNameField.sendKeys(Fullname);
	        
	        
		          // Locate the date picker input element
		            WebElement datePickerInput = driver.findElement(By.id("dateOfBirth"));

		            // Click on the date picker input to open the calendar
		            datePickerInput.click();

		            // Select the desired year
		            Select yearDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"dateOfBirth_root\"]/div/div/div/div/div[1]/select[1]")));
		            yearDropdown.selectByVisibleText("2022"); 

		            // Select the desired month
		            Select monthDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"dateOfBirth_root\"]/div/div/div/div/div[1]/select[2]")));
		            monthDropdown.selectByVisibleText("June");

		            // Select the desired day
		            WebElement desiredDay = driver.findElement(By.xpath("//*[@id=\"dateOfBirth_table\"]/tbody/tr[3]/td[4]/div"));
		            desiredDay.click();
	        
		            if (Gender.equalsIgnoreCase("Female")) {
		                WebElement genderFemaleRadioButton = driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div[5]/div/div/label[2]"));
		                genderFemaleRadioButton.click();
		            }
		            else {
		            	 WebElement genderFemaleRadioButton = driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div[5]/div/div/label[1]"));
			                genderFemaleRadioButton.click();   
		            	
		            }  
		            
		            Select cast=new Select(driver.findElement(By.id("category")));
		            cast.selectByVisibleText(caste);
		            
///////////////////////////////////////////////////////////////////////////////////////////////
		            
		            Select titleDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"title\"]")));
		            titleDropdown.selectByVisibleText(title);
		            
		            WebElement fatherFirstNameField = driver.findElement(By.id("firstName"));
		            fatherFirstNameField.sendKeys(Fname);
		            
		            WebElement fatherMiddleNameField = driver.findElement(By.id("middleName"));
		            fatherMiddleNameField.sendKeys(Mname);
		            
		            WebElement fatherLastNameField = driver.findElement(By.id("lastName"));
		            fatherLastNameField.sendKeys(Lname);
		            
		            Select relationDropdown = new Select(driver.findElement(By.id("relationshipWithStudent")));
		            relationDropdown.selectByVisibleText(Relationship);
		            
		            Select occupationDropdown = new Select(driver.findElement(By.id("occupation")));
		            occupationDropdown.selectByVisibleText(Occupation);
		            
		            WebElement contactNumberField = driver.findElement(By.id("parentsMobileNo"));
		            contactNumberField.sendKeys(String.valueOf(contactNumber));
		            
		            WebElement salaryField = driver.findElement(By.id("familyIncome"));
		            salaryField.sendKeys(String.valueOf(familyincome));
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		            
		            WebElement addressField = driver.findElement(By.id("address"));
		            addressField.sendKeys(address);

		            driver.findElement(By.id("pincode")).sendKeys(String.valueOf(pin));
		            driver.findElement(By.id("pincode")).sendKeys(Keys.ARROW_DOWN);
		            driver.findElement(By.id("pincode")).sendKeys(Keys.ENTER);
		            
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Bank Details:
		            WebElement AccountNoField = driver.findElement(By.id("bankAccNo"));
		            AccountNoField.sendKeys(String.valueOf(accountNumber));
		            
		            WebElement ReAccountNoField = driver.findElement(By.id("reEnterBankAccNo"));
		            ReAccountNoField.sendKeys(String.valueOf(reEnter));
		            
		            driver.findElement(By.id("ifscCode")).sendKeys(String.valueOf(ifsc_Code));
		            Thread.sleep(2000);
		            driver.findElement(By.id("ifscCode")).sendKeys(Keys.ARROW_DOWN);
		            driver.findElement(By.id("ifscCode")).sendKeys(Keys.TAB);
		          
		            WebElement BanknameField = driver.findElement(By.id("nameAsPerBankPassbook"));
		            BanknameField.sendKeys(Bname);
		           
		            
		            //Save option 
		            driver.findElement(By.id("saveProfile")).click();
		           
		            
		            //Current Academic details
		            
			        driver.findElement(By.cssSelector("#headingFour .mb-0")).click();
			            
			        
			        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(13));
                    driver.findElement(By.cssSelector(".round:nth-child(1) .lever")).click();
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                    String jsScript = "console.log('JavaScript executed successfully!');";
                    jsExecutor.executeScript(jsScript);

			    
			        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(20));

			        WebElement element = wait0.until(ExpectedConditions.elementToBeClickable(By.id("currAcadCourseLevelId")));
			        element.click();

			        Select courselevel = new Select(driver.findElement(By.id("currAcadCourseLevelId")));
			        courselevel.selectByVisibleText(Courselevel);  
			     //   element.sendKeys(Keys.ENTER);
			        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			        
			        driver.findElement(By.id("currAcadCourseName")).sendKeys(CourseName);
			        Thread.sleep(2000);
			        driver.findElement(By.id("currAcadCourseName")).sendKeys(Keys.ARROW_DOWN);
			        driver.findElement(By.id("currAcadCourseName")).sendKeys(Keys.TAB);
			        
			        Thread.sleep(2000);
			        driver.findElement(By.id("currAcadInstituteName")).sendKeys(InstituteName);
			        Thread.sleep(2000);
			        driver.findElement(By.id("currAcadInstituteName")).sendKeys(Keys.ARROW_DOWN);
			        driver.findElement(By.id("currAcadInstituteName")).sendKeys(Keys.TAB);
			        
			   //     driver.findElement(By.id("currentFees")).sendKeys(String.valueOf(TuitionFees));  
			       
//Tuitions Fees :
			        
			        driver.findElement(By.id("tutionFees")).click();
		            driver.findElement(By.id("tutionFees")).sendKeys(String.valueOf(TuitionFees)); 
// NonTuitions :		            
		            driver.findElement(By.id("nonTutionFees")).click();
		            driver.findElement(By.id("nonTutionFees")).sendKeys(String.valueOf( NonTuitions));
		      
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Save option :
		            driver.findElement(By.id("headingThree")).click();
		            
//Class 10 :
		            
	//School Name :	            
		            driver.findElement(By.id("edu_name_of_institute_other_384")).sendKeys(NOInstitute);   
		          
 // State
		            Select state = new Select(driver.findElement(By.id("edu_state_other_384")));
		            state.selectByVisibleText(State);
//Date :
	/*
		            //Month and Year of Passing:
		            driver.findElement(By.xpath("//*[@id=\"edu_year_of_passing_384\"]")).click();
		            
		         // Select the desired year
		            WebElement yearElement = driver.findElement(By.xpath("//div[@class='calendar']//span[contains(text(), '2017')]")); 
		            yearElement.click();
		            Thread.sleep(2000);
		            
		            // Locate the month dropdown
		            WebElement monthDropdown1 = driver.findElement(By.id("month")); 

		            // Select the desired month
		            Select month = new Select(driver.findElement(By.xpath("/html/body/div[15]/div[2]/table/tbody/tr/td/span[5]")));
		            month.selectByVisibleText("may"); 
		            Thread.sleep(2000);
		      //      Thread.sleep(10000);    
		  */          
		       
//Marks obtain :
		            
		                WebElement markCheckbox = driver.findElement(By.id("marksCGPA_384"));
		                JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
		                jsExecutor3.executeScript("arguments[0].click();", markCheckbox);
		       
//Marks obtain : 
		                //Mark optain
			            driver.findElement(By.xpath("//*[@id=\"marksObtained_384\"]")).sendKeys(String.valueOf(MarksObtain ));
			            Thread.sleep(2000);
			            //Out of Total Marks
			            driver.findElement(By.xpath("//*[@id=\"totalMarks_384\"]")).sendKeys(String.valueOf(OutOfTotal));
			            driver.findElement(By.id("edu_percentage_marks_384")).click();
			            Thread.sleep(5000);
			            driver.findElement(By.xpath("//*[@id=\"saveProfile\"]"));     
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		            
//Class 12 :
//Name of institute :	            
			            driver.findElement(By.xpath("//*[@id=\"edu_name_of_institute_other_386\"]")).sendKeys(SchoolName);
			        //    Thread.sleep(2000);
// State
			             Select state1 = new Select(driver.findElement(By.id("edu_state_other_386")));
			              state1.selectByVisibleText(State1);
			         //     Thread.sleep(2000);     
		          
 //12 marks :           
	                WebElement markCheckbox1 = driver.findElement(By.id("marksCGPA_386"));

	                // Click the checkbox using JavaScript executor
	                JavascriptExecutor jsExecutor4 = (JavascriptExecutor) driver;
	                jsExecutor4.executeScript("arguments[0].click();", markCheckbox1);
            
 //Marks obtain :
	                 WebElement mark1 = driver.findElement(By.xpath("//*[@id=\"pastEduDtlsDiv_386\"]/div[4]/div[2]/div/label")); 
		             mark1.click();
		             Thread.sleep(2000);
		             driver.findElement(By.id("marksObtained_386")).sendKeys(String.valueOf(MObtain));
	        
//Total MArks :
		              driver.findElement(By.id("totalMarks_386")).sendKeys(String.valueOf(Tmark));
	                  driver.findElement(By.id("edu_percentage_marks_386")).click();
	              //    Thread.sleep(5000);
			        
//Diploma :
	                  
 // Name of the Institute *************************************  
	                  WebDriverWait waitT = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	                  WebElement elementT = waitT.until(ExpectedConditions.elementToBeClickable(By.id("edu_name_of_institute_3")));
	                  JavascriptExecutor executor = (JavascriptExecutor) driver;
	                  executor.executeScript("arguments[0].value = arguments[1];", elementT, DCollege);
	                  driver.findElement(By.id("edu_name_of_institute_3")).click();
// State ********************
	        //          driver.findElement(By.id("edu_state_3")).sendKeys(State2 );

	                  
// Diploma marks : 
	                WebElement markCheckboxx = driver.findElement(By.xpath("//*[@id=\"pastEduDtlsDiv_3\"]/div[6]/div[2]/div"));

 	                // Click the checkbox using JavaScript executor
 	                JavascriptExecutor jsExecutor44 = (JavascriptExecutor) driver;
 	                jsExecutor44.executeScript("arguments[0].click();", markCheckboxx);
//Marks obtain :
 	 	            driver.findElement(By.id("marksObtained_3")).sendKeys(String.valueOf(DMObtain));
 	 	       //     Thread.sleep(2000);
  //Out of Total Marks
 	 	            driver.findElement(By.id("totalMarks_3")).sendKeys(String.valueOf(DTMarks));
 	 	            driver.findElement(By.id("edu_percentage_marks_3")).click();
	                  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Graduation:
 	 	          WebDriverWait waitTT = new WebDriverWait(driver, Duration.ofSeconds(20)); 
                  WebElement elementTT = waitTT.until(ExpectedConditions.elementToBeClickable(By.id("edu_name_of_institute_4")));
                  JavascriptExecutor executorT = (JavascriptExecutor) driver;
                  executorT.executeScript("arguments[0].value = arguments[1];", elementTT, GCollege);
                  driver.findElement(By.id("edu_name_of_institute_4")).click();
 	 	            
//CGPA :
                  /*
                  // Find the checkbox element
	                WebElement markCheckbox11 = driver.findElement(By.xpath("//*[@id=\"pastEduDtlsDiv_4\"]/div[6]/div[2]/div/label"));

	                // Click the checkbox using JavaScript executor
	                JavascriptExecutor jsExecutor31 = (JavascriptExecutor) driver;
	                jsExecutor31.executeScript("arguments[0].click();", markCheckbox11);
                  */
                  
                //Mark optain
  	            driver.findElement(By.id("cgpaObtained_4")).sendKeys(String.valueOf(GMObtain));
  	          //  Thread.sleep(2000);
  	            //Out of Total Marks
  	            driver.findElement(By.id("totalCgpa_4")).sendKeys(String.valueOf(GTMarks));
  	         //   driver.findElement(By.id("edu_percentage_marks_4")).click();
                  
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Current Year :
  	 
  	        Select s = new Select(driver.findElement(By.id("noOfYrsCompleted")));
  	        s.selectByVisibleText(CYear);
  	        driver.findElement(By.id("//*[@id=\"noOfYrsCompleted\"]")).sendKeys(Keys.ENTER);
  	        driver.findElement(By.id("//*[@id=\"noOfYrsCompleted\"]")).sendKeys(Keys.TAB);   
  	        
  	        driver.findElement(By.id("currentCgpaObtainedlbl")).sendKeys(String.valueOf(CYmarks));
  	        driver.findElement(By.id("currentTotalCgpa")).sendKeys(String.valueOf(CYCGPA));  
  	        
	                  
	                  
  	          
	        }        
	        
	}

}
