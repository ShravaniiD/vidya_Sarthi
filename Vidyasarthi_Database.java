package dbmanager;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.util.UriComponentsBuilder;

import com.twocaptcha.TwoCaptcha;
import com.twocaptcha.captcha.Normal;

public class Vidyasarthi_Database {
	
	 private static final Logger logger = Logger.getLogger(Demo_Script.class.getName());
	  private static FileHandler fileHandler;

	  private static void ensureDirectoryWritable(File directory) {
			 
		  try {
            Path path = FileSystems.getDefault().getPath(directory.getAbsolutePath());

            // Check if the directory exists
            if (Files.exists(path)) {
                // Get current permissions
                Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(path);
                Set<PosixFilePermission> currentPermissions = Files.getPosixFilePermissions(path);
                System.out.println("Current permissions: " + currentPermissions);
                // Add write permission for the owner (you can customize this based on your needs)
                permissions.add(PosixFilePermission.OWNER_WRITE);

                // Set updated permissions
                Files.setPosixFilePermissions(path, permissions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		

  private static int scriptRunCounter = 0;

  public static void main(String[] args) throws IOException, InterruptedException {
		
			
		        ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
		        options.addArguments("--disable-notifications");
		        WebDriver driver = new ChromeDriver(options);
		        driver.manage().window().maximize();
				
 
        
		       File src = new File("C:\\Users\\Shravani\\Desktop\\Excel_data\\Vidyasarthi_DataBase_Testing.xlsx");
		        FileInputStream fis = new FileInputStream(src);
		        
		        XSSFWorkbook xsf = new XSSFWorkbook(fis);
		        XSSFSheet sheet = xsf.getSheetAt(0);	
		   
        
		        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
		            XSSFRow row = sheet.getRow(rowNum);
		            
		            String vidyasaarthi_email  = row.getCell(0).getStringCellValue();
		            String vidyasaarthi_password = row.getCell(1).getStringCellValue(); 
		            String fullname = row.getCell(2).getStringCellValue(); 
		            String  Photo_doc = row.getCell(3).getStringCellValue(); 
		            String gender = row.getCell(5).getStringCellValue();
		            String caste_id = row.getCell(6).getStringCellValue(); 
		            String marital_status = row.getCell(7).getStringCellValue(); 
		            String aadhar_card = row.getCell(8).getStringCellValue(); 
//*******Parent Details 
		            String Parent_Title = row.getCell(9).getStringCellValue(); 
		            String Father_Name = row.getCell(10).getStringCellValue(); 
		            String Middle_Name = row.getCell(11).getStringCellValue(); 
		            String Last_Name = row.getCell(12).getStringCellValue(); 
		            String Relationship_with_Student = row.getCell(13).getStringCellValue(); 
		            String father_occupation = row.getCell(14).getStringCellValue(); 
		            long parents_mobile =(long) row.getCell(15).getNumericCellValue();
		            long annual_income =(long) row.getCell(16).getNumericCellValue();
		            String income_certificate = row.getCell(17).getStringCellValue();
		            
//*********Address Info 
		            String permanent_add = row.getCell(18).getStringCellValue(); 
		            long permanent_pincode =(long) row.getCell(19).getNumericCellValue();
		            String address_proof = row.getCell(20).getStringCellValue();
		            
//*****Bank Details : 
		            
		            long account_number =(long) row.getCell(21).getNumericCellValue(); 
		            String bank_ifsc = row.getCell(22).getStringCellValue();
		            String Bank_Name = row.getCell(23).getStringCellValue();
		            String bank_passbook = row.getCell(24).getStringCellValue();
		            
//******Current Course Details : 
		           String student_qualification_level = row.getCell(25).getStringCellValue();
		           String current_course_name = row.getCell(26).getStringCellValue();
		           String current_course_college_name  = row.getCell(27).getStringCellValue(); 
		           long tution_fees =(long) row.getCell(28).getNumericCellValue();
		           String  fees_structure = row.getCell(29).getStringCellValue();
		           long non_tution_fees =(long) row.getCell(30).getNumericCellValue(); 
		           String currentyear_fees_reciept = row.getCell(31).getStringCellValue();
		           String admission_letter = row.getCell(32).getStringCellValue();
// ****!10 th details :
		           String Name_of_Institute_10_Class = row.getCell(33).getStringCellValue();
		           String ssc_qualification_institute_state = row.getCell(34).getStringCellValue();
		           long Total_Marks_Obtained_10 =(long) row.getCell(36).getNumericCellValue(); 
		           long Out_of_Total_Marks_10  =(long) row.getCell(37).getNumericCellValue(); 
		           String Upload_Marksheet_10 = row.getCell(38).getStringCellValue();
//******12 th details :
		           String Past_Qulification  = row.getCell(39).getStringCellValue();
		           String Name_of_Institute_12th = row.getCell(40).getStringCellValue();
		           String State_12th = row.getCell(41).getStringCellValue(); 
		      //     String Month_and_Year_of_Passing = row.getCell(42).getStringCellValue(); 
		           long  Total_Marks_Obtained =(long) row.getCell(43).getNumericCellValue(); 
		           long  Out_of_Total_Marks =(long) row.getCell(44).getNumericCellValue(); 
		           String Upload_Marksheet_12th = row.getCell(45).getStringCellValue();
		           
//******Current Details : 
		           
		           String Previous_Year_of_Current_Course = row.getCell(46).getStringCellValue();
		           long Total_Marks_Obtained_Current_Course  =(long) row.getCell(47).getNumericCellValue();
		           long Out_of_Total_Marks_Current_Course  =(long) row.getCell(48).getNumericCellValue();
		           String Upload_Marksheet_Current_Course = row.getCell(49).getStringCellValue();
		           
//Graduation : 
		           String Graduation_College_Name = row.getCell(50).getStringCellValue();  
		           long Passing_Year  =(long) row.getCell(51).getNumericCellValue();
		           long Graduation_Marks_Obtain  =(long) row.getCell(52).getNumericCellValue();
		           long Graduation_OutOf_Marks =(long) row.getCell(53).getNumericCellValue();
		           String Graduation_Marksheet = row.getCell(54).getStringCellValue(); 
		           
		           
        
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
		            emailInput.sendKeys(vidyasaarthi_email);

		            WebElement passwordInput = driver.findElement(By.name("password"));
		            passwordInput.sendKeys(vidyasaarthi_password);
		     
//Capcha Enter : 
		            
		            Thread.sleep(1000);
		            WebElement imageElement = driver.findElement(By.xpath("//*[@id=\"command\"]/div[8]/div[1]/div"));
		            File src1 = imageElement.getScreenshotAs(OutputType.FILE);

		            String relativePath = "Captcha_ScreenShot/captcha.png";
		            // Save the screenshot to the specified location on the file system
		            File destFile = new File(relativePath);
		       
		            
		            File parentDir = destFile.getParentFile();
		            System.out.println("Parent directory exists: " + parentDir.exists());
		            if (!parentDir.exists()) {
		                parentDir.mkdirs();
		            }

		            // Ensure that the file exists
		            System.out.println("File exists: " + destFile.exists());
		            
		            ensureDirectoryWritable(destFile.getParentFile());
		            
		            FileUtils.copyFile(src1, destFile);
		            System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());

		            // Use the file path directly
		            String absolutePathX = destFile.getAbsolutePath();

		            // Rest of your code remains unchanged
		            TwoCaptcha solver = new TwoCaptcha("48378eaf86e24c819d93d5105499d4a2");
		            Normal captcha = new Normal();
		            captcha.setFile(absolutePathX);
		            captcha.setMinLen(4);
		            captcha.setMaxLen(20);
		            captcha.setCaseSensitive(true);
		            captcha.setLang("en");

		            Thread.sleep(1000);

		            try {
		                solver.solve(captcha);
		                String captchaCode = captcha.getCode();
		                System.out.println("Captcha solved: " + captchaCode);

		                
		                WebElement captchaInputField = driver.findElement(By.id("txtInput"));

		                // Input the captcha code into the field
		                captchaInputField.sendKeys(captchaCode);

		            } catch (Exception e) {
		                System.out.println("Error occurred: " + e.getMessage());
		            }

		   	      
		     
		            logger.info("Captcha uploaded sucessfully");
		            
// Click on login button : 
		            
	            WebElement submitButton = driver.findElement(By.id("login"));
	            submitButton.click();
	                
//close button popup
	            driver.findElement(By.xpath("//*[@id=\"modalonlinetest\"]/div/div/div[2]/a")).click();
	            
		          
	            
//click on profile button 
	             driver.findElement(By.xpath("//*[@id=\"studentMenu\"]/li[3]/a")).click();
	             Thread.sleep(2000);
	       		            
//Enter your name : 
	             
	             driver.findElement(By.id("fullName")).clear();            
		            
	             WebElement fullNameField = driver.findElement(By.id("fullName"));
		         fullNameField.sendKeys(fullname);   
		            
// Enter the photo :        
		     	try {
		     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\A.jpg";
	        	    WebElement fileInput = driver.findElement(By.id("applicant_Photo")); 
                    Thread.sleep(2000);
	        	
	        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Photo_doc).build().toUriString();  
	        	    // Download the file from S3 to your local machine
	        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

	        	    // Provide the local file path to the file input element
	        	    fileInput.sendKeys(localFilePath);

	        	    // Delete the downloaded file
	        	    File downloadedFile = new File(localFilePath);
	        	    if (downloadedFile.exists()) {
	        	        downloadedFile.delete();
	        	        System.out.println("Downloaded file deleted successfully.");
	        	    }  
	        	} catch (IOException e) {
	        	    System.out.println("Not work");
	        	    e.printStackTrace();
	        	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
// DOB : 
		     	
		     	  DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");
		     	  XSSFCell dateCell = row.getCell(0); // Assuming date is in the first column
		          LocalDateTime localDateTime = dateCell.getLocalDateTimeCellValue();
		     	
		          
	 		       int year = localDateTime.getYear();
	               String month = localDateTime.format(monthFormatter); 
	               int day = localDateTime.getDayOfMonth();  
	                  
		     	
		     	
	               WebElement datePicker = driver.findElement(By.id("dateOfBirth")); // Replace with your input field ID
	               datePicker.click();

	               // Select year
	               Select yearDropdown = new Select(driver.findElement(By.className("picker__select--year"))); // Replace with the class or id of the year dropdown
	               yearDropdown.selectByVisibleText(String.valueOf(year));

	               // Select month
	               Select monthDropdown = new Select(driver.findElement(By.className("picker__select--month"))); // Replace with the class or id of the month dropdown
	               monthDropdown.selectByVisibleText(month);

	               // Select day
	               WebElement dayElement = driver.findElement(By.xpath("//*[@id=\"dateOfBirth_table\"]/tbody" + day + "']")); // Replace with the XPath to find the specific date
	               dayElement.click();
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		     	
		    
////////////////////////////////////////////////////////////////////////////////////////////////////////////		            
//Gender : 		            
		            
		        if (gender.equals("FEMALE")) {
	                WebElement genderFemaleRadioButton = driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div[5]/div/div/label[2]"));
	                genderFemaleRadioButton.click();
	            }
	            else {
	            	 WebElement genderFemaleRadioButton = driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div[5]/div/div/label[1]"));
		                genderFemaleRadioButton.click();   
	            	
	            }          
//Caste Category : 
		        Select cast=new Select(driver.findElement(By.id("category")));
	            cast.selectByVisibleText(caste_id);
		            
//Marriatal Status :         
        
	            Select mariatal_Status=new Select(driver.findElement(By.id("maritalStatus")));
	            mariatal_Status.selectByVisibleText(marital_status);
        
 //AdharCard Upload : proof Of Identity : 
	            
	        	try {
		     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\AdharCard.jpg";
	        	    WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"id_Proof\"]")); 
                    Thread.sleep(2000);
	        	
	        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(aadhar_card).build().toUriString();  
	        	    // Download the file from S3 to your local machine
	        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

	        	    // Provide the local file path to the file input element
	        	    fileInput.sendKeys(localFilePath);

	        	    // Delete the downloaded file
	        	    File downloadedFile = new File(localFilePath);
	        	    if (downloadedFile.exists()) {
	        	        downloadedFile.delete();
	        	        System.out.println("Downloaded file deleted successfully.");
	        	    }  
	        	} catch (IOException e) {
	        	    System.out.println("Not work");
	        	    e.printStackTrace();
	        	}
	        	
	        	
// Parents Details : 
	        	
	        	  Select titleDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"title\"]")));
		            titleDropdown.selectByVisibleText(Parent_Title);
		            
		            driver.findElement(By.id("firstName")).clear(); 
		            WebElement fatherFirstNameField = driver.findElement(By.id("firstName"));
		            fatherFirstNameField.sendKeys(Father_Name);
		            
		            driver.findElement(By.id("middleName")).clear(); 
		            WebElement fatherMiddleNameField = driver.findElement(By.id("middleName"));
		            fatherMiddleNameField.sendKeys(Middle_Name);
		            
		            driver.findElement(By.id("lastName")).clear();
		            WebElement fatherLastNameField = driver.findElement(By.id("lastName"));
		            fatherLastNameField.sendKeys(Last_Name);
		            
		            Select relationDropdown = new Select(driver.findElement(By.id("relationshipWithStudent")));
		            relationDropdown.selectByVisibleText(Relationship_with_Student);
		            
		            Select occupationDropdown = new Select(driver.findElement(By.id("occupation")));
		            occupationDropdown.selectByVisibleText(father_occupation);
		            
		            driver.findElement(By.id("parentsMobileNo")).clear();
		            WebElement contactNumberField = driver.findElement(By.id("parentsMobileNo"));
		            contactNumberField.sendKeys(String.valueOf(parents_mobile));
		            
		            driver.findElement(By.id("familyIncome")).clear();
		            WebElement salaryField = driver.findElement(By.id("familyIncome"));
		            salaryField.sendKeys(String.valueOf(annual_income));

//Income Certificate :
		            Thread.sleep(2000);
		            
		        	try {
			     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\IncomeCertification.jpg";
			     		System.out.println("Print localPath"+localFilePath);
		        	    WebElement fileInput = driver.findElement(By.id("proof_Of_Income")); 
	                    Thread.sleep(2000);
		        	
		        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(income_certificate).build().toUriString();  
		        	    // Download the file from S3 to your local machine
		        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

		        	    // Provide the local file path to the file input element
		        	    fileInput.sendKeys(localFilePath);

		        	    // Delete the downloaded file
		        	    File downloadedFile = new File(localFilePath);
		        	    if (downloadedFile.exists()) {
		        	        downloadedFile.delete();
		        	        System.out.println("Downloaded file deleted successfully.");
		        	    }  
		        	} catch (IOException e) {
		        	    System.out.println("Not work");
		        	    e.printStackTrace();
		        	}
	      
//Address  Information : 
		        	
		            driver.findElement(By.id("address")).clear();
		            WebElement addressField = driver.findElement(By.id("address"));
		            addressField.sendKeys(permanent_add);

		            driver.findElement(By.id("pincode")).sendKeys(String.valueOf(permanent_pincode));
		            driver.findElement(By.id("pincode")).sendKeys(Keys.ARROW_DOWN);
		            driver.findElement(By.id("pincode")).sendKeys(Keys.ENTER);

//Address proof	 :
		        	try {
			     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\address_proof.jpg";
			     		System.out.println("Print localPath"+localFilePath);
		        	    WebElement fileInput = driver.findElement(By.id("address_Proof")); 
	                    Thread.sleep(2000);
		        	
		        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(address_proof).build().toUriString();  
		        	    // Download the file from S3 to your local machine
		        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

		        	    // Provide the local file path to the file input element
		        	    fileInput.sendKeys(localFilePath);

		        	    // Delete the downloaded file
		        	    File downloadedFile = new File(localFilePath);
		        	    if (downloadedFile.exists()) {
		        	        downloadedFile.delete();
		        	        System.out.println("Downloaded file deleted successfully.");
		        	    }  
		        	} catch (IOException e) {
		        	    System.out.println("Not work");
		        	    e.printStackTrace();
		        	}
	        	
//*********Bank Details :
		        	
		        	    driver.findElement(By.id("bankAccNo")).clear();
		            	WebElement AccountNoField = driver.findElement(By.id("bankAccNo"));
			            AccountNoField.sendKeys(String.valueOf(account_number));
			            
			            driver.findElement(By.id("reEnterBankAccNo")).clear();
			            WebElement ReAccountNoField = driver.findElement(By.id("reEnterBankAccNo"));
			            ReAccountNoField.sendKeys(String.valueOf(account_number));
			            
			            driver.findElement(By.id("ifscCode")).clear();
			            driver.findElement(By.id("ifscCode")).sendKeys(String.valueOf(bank_ifsc));
			            Thread.sleep(2000);
			            
			            driver.findElement(By.id("ifscCode")).clear();
			            driver.findElement(By.id("ifscCode")).sendKeys(Keys.ARROW_DOWN);
			            driver.findElement(By.id("ifscCode")).sendKeys(Keys.TAB);
			          
			            driver.findElement(By.id("nameAsPerBankPassbook")).clear();
			            WebElement BanknameField = driver.findElement(By.id("nameAsPerBankPassbook"));
			            BanknameField.sendKeys(Bank_Name);
			           
//Upload Bank details : 
			            
			            try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Bank_Passbook.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("bank_Passbook")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(bank_passbook).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}
      
			            
			        		            
  //Save option 
			       //     driver.findElement(By.id("saveProfile")).click();
			            
//****Current Course Details :			           
			           
			            driver.findElement(By.cssSelector("#headingFour .mb-0")).click();   
		        	
//Course Level : 		        	
                  
			        Thread.sleep(2000);
			        Select courselevel = new Select(driver.findElement(By.id("currAcadCourseLevelId")));
			        courselevel.selectByVisibleText(student_qualification_level);  
        
			       
			  //         Thread.sleep(5000);

			       
			        
				     
			        
			        driver.findElement(By.id("currAcadCourseName")).sendKeys(current_course_name);
			        Thread.sleep(2000);
			        driver.findElement(By.id("currAcadCourseName")).sendKeys(Keys.ARROW_DOWN);
			        driver.findElement(By.id("currAcadCourseName")).sendKeys(Keys.TAB);
			       
			        
			        
			        
			        
	            
	     //       Thread.sleep(2000);
			        
//Current Institute Name : 	               
			        String inputText = current_course_college_name ;
			        WebElement inputField = driver.findElement(By.id("currAcadInstituteName"));
			        for (char c : inputText.toCharArray()) {
			            inputField.sendKeys(String.valueOf(c));
			          
			            Thread.sleep(1000); 
			      }
			        inputField.sendKeys(Keys.ARROW_DOWN);
				    inputField.sendKeys(Keys.TAB);
			        
			        
			        
			        
			        
//Tuition Fees : 
			        
			  //    driver.findElement(By.id("tutionFees")).click();
		            driver.findElement(By.id("tutionFees")).sendKeys(String.valueOf(tution_fees));    
	            
	            
// Upload fee structure : 
		            
		            try {
			     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Fee_Structure.jpg";
			     		System.out.println("Print localPath"+localFilePath);
		        	    WebElement fileInput = driver.findElement(By.id("expense_Of_Course")); 
	                    Thread.sleep(2000);
		        	
		        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(fees_structure).build().toUriString();  
		        	    // Download the file from S3 to your local machine
		        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

		        	    // Provide the local file path to the file input element
		        	    fileInput.sendKeys(localFilePath);

		        	    // Delete the downloaded file
		        	    File downloadedFile = new File(localFilePath);
		        	    if (downloadedFile.exists()) {
		        	        downloadedFile.delete();
		        	        System.out.println("Downloaded file deleted successfully.");
		        	    }  
		        	} catch (IOException e) {
		        	    System.out.println("Not work");
		        	    e.printStackTrace();
		        	}
		            
		            
		            
// Non Tuition Fees : 
		                
		            
		            driver.findElement(By.id("nonTutionFees")).click();
		            driver.findElement(By.id("nonTutionFees")).sendKeys(String.valueOf(non_tution_fees));   
		            
// Upload Fee recipt 		            
		            
		            
		            
		            try {
			     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Free_Recipt.jpg";
			     		System.out.println("Print localPath"+localFilePath);
		        	    WebElement fileInput = driver.findElement(By.id("TutitionNonTuitionReceipt")); 
	                    Thread.sleep(2000);
		        	
		        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(currentyear_fees_reciept).build().toUriString();  
		        	    // Download the file from S3 to your local machine
		        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

		        	    // Provide the local file path to the file input element
		        	    fileInput.sendKeys(localFilePath);

		        	    // Delete the downloaded file
		        	    File downloadedFile = new File(localFilePath);
		        	    if (downloadedFile.exists()) {
		        	        downloadedFile.delete();
		        	        System.out.println("Downloaded file deleted successfully.");
		        	    }  
		        	} catch (IOException e) {
		        	    System.out.println("Not work");
		        	    e.printStackTrace();
		        	}   
		         
		            
//admission_letter  :		            
		            
		            
		            try {
			     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Admission_Letter.jpg";
			     		System.out.println("Print localPath"+localFilePath);
		        	    WebElement fileInput = driver.findElement(By.id("Admission_Letter")); 
	                    Thread.sleep(2000);
		        	
		        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(admission_letter).build().toUriString();  
		        	    // Download the file from S3 to your local machine
		        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

		        	    // Provide the local file path to the file input element
		        	    fileInput.sendKeys(localFilePath);

		        	    // Delete the downloaded file
		        	    File downloadedFile = new File(localFilePath);
		        	    if (downloadedFile.exists()) {
		        	        downloadedFile.delete();
		        	        System.out.println("Downloaded file deleted successfully.");
		        	    }  
		        	} catch (IOException e) {
		        	    System.out.println("Not work");
		        	    e.printStackTrace();
		        	} 
		            
////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Educational Details : 
		            driver.findElement(By.id("headingThree")).click();
//For Higher Secondary Student : *******************************************************************************		            
		            if (student_qualification_level.equals("Higher Secondary")) {
		            	
		            
//School Name : 		            	
		            	 driver.findElement(By.id("edu_name_of_institute_other_384")).sendKeys(Name_of_Institute_10_Class);   
				          
// State :
			            Select state = new Select(driver.findElement(By.id("edu_state_other_384")));
			            state.selectByVisibleText(ssc_qualification_institute_state);
		            	
// Date : 
			            
			            
//Persentage : 
//Marks obtain :
			/*            
		                WebElement markCheckbox = driver.findElement(By.id("marksCGPA_384"));
		                JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
		                jsExecutor3.executeScript("arguments[0].click();", markCheckbox);
		       */
//Marks obtain : 
		                //Mark optain
			            driver.findElement(By.xpath("//*[@id=\"marksObtained_384\"]")).sendKeys(String.valueOf(Total_Marks_Obtained_10));
			            Thread.sleep(2000);
			            //Out of Total Marks
			            driver.findElement(By.xpath("//*[@id=\"totalMarks_384\"]")).sendKeys(String.valueOf(Out_of_Total_Marks_10));
			            driver.findElement(By.id("edu_percentage_marks_384")).click();
			            Thread.sleep(5000);
//Marksheet Upload : 
			            
			            
			            try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\SSC_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("ssc_Marksheet")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_10).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}     
			            
			           }
		            
//****For Diploma Student : *************************************************************************************
		            
		            else if (student_qualification_level.equals("Diploma")) {
//10 Th Details ***********		            	
//College Name : 		            	
		           	 driver.findElement(By.id("edu_name_of_institute_other_384")).sendKeys(Name_of_Institute_10_Class);  
		            	 
		                 	            	 
				          
//State :
			            Select state = new Select(driver.findElement(By.id("edu_state_other_384")));
			            state.selectByVisibleText(ssc_qualification_institute_state);
		            	
//Date : 
			            
			            
//Persentage : 
//Marks obtain :
			/*            
		                WebElement markCheckbox = driver.findElement(By.id("marksCGPA_384"));
		                JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
		                jsExecutor3.executeScript("arguments[0].click();", markCheckbox);
		       */
//Marks obtain : 
		                //Mark optain
			            driver.findElement(By.xpath("//*[@id=\"marksObtained_384\"]")).sendKeys(String.valueOf(Total_Marks_Obtained_10));
			            Thread.sleep(2000);
			            //Out of Total Marks
			            driver.findElement(By.xpath("//*[@id=\"totalMarks_384\"]")).sendKeys(String.valueOf(Out_of_Total_Marks_10));
			            driver.findElement(By.id("edu_percentage_marks_384")).click();
			            Thread.sleep(5000);
//Marksheet Upload : 
			            
			            
			            try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\SSC_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("ssc_Marksheet")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_10).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}  
/////////////			            
			            
			            
// 12  Details : 
			            
			            
			          //School Name : 		            	
		            	 driver.findElement(By.id("edu_name_of_institute_other_386")).sendKeys(Name_of_Institute_12th);   
				          
//State :
			            Select state_12 = new Select(driver.findElement(By.id("edu_state_other_386")));
			            state_12.selectByVisibleText(State_12th);
		            	
//Date : 
			            
			            
//Persentage : 
//Marks obtain :
			/*            
		                WebElement markCheckbox = driver.findElement(By.id("marksCGPA_384"));
		                JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
		                jsExecutor3.executeScript("arguments[0].click();", markCheckbox);
		       */
//Marks obtain : 
		                //Mark optain
			            driver.findElement(By.id("marksObtained_386")).sendKeys(String.valueOf(Total_Marks_Obtained));
			            Thread.sleep(2000);
			            //Out of Total Marks
			            driver.findElement(By.id("totalMarks_386")).sendKeys(String.valueOf(Out_of_Total_Marks));
			            driver.findElement(By.id("edu_percentage_marks_386")).click();
			            Thread.sleep(5000);
//Marksheet Upload : 
			            
			            
			            try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\HSC_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("hsc_Marksheet")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_12th).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}     
			                        
//Current Cource : 
			           if (Previous_Year_of_Current_Course.equals("First Year")) {
			        	   
//Previous Year of Current Course :
			        	   
			        	   
			        	   Select Previes_Year  = new Select(driver.findElement(By.id("noOfYrsCompleted")));
				           Previes_Year.selectByVisibleText(Previous_Year_of_Current_Course);
				              
//Percentage : 
				           
// Total Marks Obtained : 
				           
				       driver.findElement(By.id("currentMarksObtained")).sendKeys(String.valueOf(Total_Marks_Obtained_Current_Course));
				       Thread.sleep(2000);
				            //Out of Total Marks
				       driver.findElement(By.id("currentTotalMarks")).sendKeys(String.valueOf(Out_of_Total_Marks_Current_Course));
				       driver.findElement(By.id("currAcadMarksPercentage")).click();    
				           
//Upload Document : Upload_Marksheet_Current_Course    
				           
				       try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Current_Course_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("marksheet_LQ")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_Current_Course).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}   
				       	   
			           }
			           else if(Previous_Year_of_Current_Course.equals("None")) {
			        	     
			           Select Previes_Year  = new Select(driver.findElement(By.id("noOfYrsCompleted")));
			           Previes_Year.selectByVisibleText(Previous_Year_of_Current_Course);
			           
			           }
			            
			              
			           }
//For Under Graduate Student : **************************************************************************************
		            
		            else if (student_qualification_level.equals("Under Graduate")||student_qualification_level.equals("Post Graduate/PG Diploma")) {
//10 th details :*************************		            	
//School Name : 		            	
		            	 driver.findElement(By.id("edu_name_of_institute_other_384")).sendKeys(Name_of_Institute_10_Class);   
				          
//State :
			            Select state = new Select(driver.findElement(By.id("edu_state_other_384")));
			            state.selectByVisibleText(ssc_qualification_institute_state);
		            	
//Date : 
			            
			            
//Persentage : 
//Marks obtain :
			/*            
		                WebElement markCheckbox = driver.findElement(By.id("marksCGPA_384"));
		                JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
		                jsExecutor3.executeScript("arguments[0].click();", markCheckbox);
		       */
//Marks obtain : 
		                //Mark optain
			            driver.findElement(By.xpath("//*[@id=\"marksObtained_384\"]")).sendKeys(String.valueOf(Total_Marks_Obtained_10));
			            Thread.sleep(2000);
			            //Out of Total Marks
			            driver.findElement(By.xpath("//*[@id=\"totalMarks_384\"]")).sendKeys(String.valueOf(Out_of_Total_Marks_10));
			            driver.findElement(By.id("edu_percentage_marks_384")).click();
			            Thread.sleep(5000);
//Marksheet Upload : 
			            
			            
			            try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\SSC_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("ssc_Marksheet")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_10).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}  
/////////////			            
			            
			            
//12  Details : 
			           if(Past_Qulification.equals("12 Th Standard")) { 
			            
//School Name : 		            	
		            	 driver.findElement(By.id("edu_name_of_institute_other_386")).sendKeys(Name_of_Institute_12th);   
				          
//State :
			            Select state_12 = new Select(driver.findElement(By.id("edu_state_other_386")));
			            state_12.selectByVisibleText(State_12th);
		            	
//Date : 
			            
			            
//Persentage : 
//Marks obtain :
			/*            
		                WebElement markCheckbox = driver.findElement(By.id("marksCGPA_384"));
		                JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
		                jsExecutor3.executeScript("arguments[0].click();", markCheckbox);
		       */
//Marks obtain : 
		                //Mark optain
			            driver.findElement(By.id("marksObtained_386")).sendKeys(String.valueOf(Total_Marks_Obtained));
			            Thread.sleep(2000);
			            //Out of Total Marks
			            driver.findElement(By.id("totalMarks_386")).sendKeys(String.valueOf(Out_of_Total_Marks));
			            driver.findElement(By.id("edu_percentage_marks_386")).click();
			            Thread.sleep(5000);
//Marksheet Upload : 
			            
			            
			            try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\HSC_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("hsc_Marksheet")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_12th).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}     
			              		            	
			           }
//Diploma :*****************************************************************			           
			           else if (Past_Qulification.equals("Diploma")) {
//School Name : 		            	
			           /*   	   String inputText_Diploma = Name_of_Institute_12th ;
						        WebElement inputField_Diploma = driver.findElement(By.id("edu_name_of_institute_3")); 
						       for (char c : inputText_Diploma.toCharArray()) {
						        
						       	inputField_Diploma.sendKeys(String.valueOf(c));
						       
						         Thread.sleep(2000);                  
						     }
						       
						       inputField_Diploma.sendKeys(Keys.ARROW_DOWN);
					           inputField_Diploma.sendKeys(Keys.ENTER); 
					         */

 
						        
			        	 


			        			// Compare selectedText with expected value or perform further actions

			        	   
			        	   
			        	   
			        	   
			                Thread.sleep(2000);	 
			                
			                
			                
			                
			                
			            	 
					          

			            	
	//Date : 
				            
				            
	//Persentage : 
	//Marks obtain :
				/*            
			                WebElement markCheckbox = driver.findElement(By.id("marksCGPA_384"));
			                JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
			                jsExecutor3.executeScript("arguments[0].click();", markCheckbox);
			       */
	//Marks obtain : 
			                //Mark optain
				            driver.findElement(By.id("marksObtained_3")).sendKeys(String.valueOf(Total_Marks_Obtained));
				            Thread.sleep(2000);
				            //Out of Total Marks
				            driver.findElement(By.id("totalMarks_3")).sendKeys(String.valueOf(Out_of_Total_Marks));
				            driver.findElement(By.id("edu_percentage_marks_3")).click();
				            Thread.sleep(5000);
	//Marksheet Upload : 
				            
				            
				            try {
					     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Diploma_Marksheet.jpg";
					     		System.out.println("Print localPath"+localFilePath);
				        	    WebElement fileInput = driver.findElement(By.id("diploma_Marksheet")); 
			                    Thread.sleep(2000);
				        	
				        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_12th).build().toUriString();  
				        	    // Download the file from S3 to your local machine
				        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

				        	    // Provide the local file path to the file input element
				        	    fileInput.sendKeys(localFilePath);

				        	    // Delete the downloaded file
				        	    File downloadedFile = new File(localFilePath);
				        	    if (downloadedFile.exists()) {
				        	        downloadedFile.delete();
				        	        System.out.println("Downloaded file deleted successfully.");
				        	    }  
				        	} catch (IOException e) {
				        	    System.out.println("Not work");
				        	    e.printStackTrace();
				        	}        
			        	   
			           }
//Graduation : ***************************************************************************************
//College name : 			           
			           
			           String inputText_Diploma = "Graduation_College_Name";
			           WebElement inputField_Diploma = driver.findElement(By.id("edu_name_of_institute_4")); 

			           // Simulate typing each character into the input field with a delay
			           for (char c : inputText_Diploma.toCharArray()) {
			               inputField_Diploma.sendKeys(String.valueOf(c));
			               try {
			                   Thread.sleep(2000); // Adjust delay as needed
			               } catch (InterruptedException e) {
			                   e.printStackTrace();
			               }
			           }

			           // Wait for the autocomplete options to become visible
			           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			           wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edu_name_of_institute_4")));

			           // Simulate selecting an autocomplete option
			           inputField_Diploma.sendKeys(Keys.ARROW_DOWN);
			           inputField_Diploma.sendKeys(Keys.ENTER);
    
			           
		                  
		             
			           
			           
//Total marks obtain : 
		                  
		                  
		                  // Find the checkbox element
			                WebElement markCheckbox11 = driver.findElement(By.xpath("//*[@id=\"pastEduDtlsDiv_4\"]/div[6]/div[2]/div/label"));

			                // Click the checkbox using JavaScript executor
			                JavascriptExecutor jsExecutor31 = (JavascriptExecutor) driver;
			                jsExecutor31.executeScript("arguments[0].click();", markCheckbox11);
		                  
//Mark optain
		  	            driver.findElement(By.id("cgpaObtained_4")).sendKeys(String.valueOf(Graduation_Marks_Obtain));
		  	      
//Out of Total Marks
		  	            driver.findElement(By.id("totalCgpa_4")).sendKeys(String.valueOf(Graduation_OutOf_Marks));
		  	            driver.findElement(By.xpath("//*[@id=\"edu_percentage_cgpa_4\"]")).click();	                  
		                  
//Upload Marksheet : 
		  	            
			        
		  	          try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Graduation_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("grad_Marksheet")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Graduation_Marksheet).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}          
		  	            
//Current Course Details : *****************************************
		  	          
		  	      //Current Cource : 
			           if (Previous_Year_of_Current_Course.equals("First Year")) {
			        	   
//Previous Year of Current Course :
			        	   
			        	   
			        	   Select Previes_Year  = new Select(driver.findElement(By.id("noOfYrsCompleted")));
				           Previes_Year.selectByVisibleText(Previous_Year_of_Current_Course);
				              
//Percentage : 
				           
//Total Marks Obtained : 
				           
				       driver.findElement(By.id("currentMarksObtained")).sendKeys(String.valueOf(Total_Marks_Obtained_Current_Course));
				       Thread.sleep(2000);
				            //Out of Total Marks
				       driver.findElement(By.id("currentTotalMarks")).sendKeys(String.valueOf(Out_of_Total_Marks_Current_Course));
				       driver.findElement(By.id("currAcadMarksPercentage")).click();    
				           
//Upload Document : Upload_Marksheet_Current_Course    
				           
				       try {
				     		String localFilePath ="C:\\Users\\Shravani\\Desktop\\S3_URL_File\\Current_Course_Marksheet.jpg";
				     		System.out.println("Print localPath"+localFilePath);
			        	    WebElement fileInput = driver.findElement(By.id("marksheet_LQ")); 
		                    Thread.sleep(2000);
			        	
			        	    String encodedS3Url = UriComponentsBuilder.fromHttpUrl(Upload_Marksheet_Current_Course).build().toUriString();  
			        	    // Download the file from S3 to your local machine
			        	    FileUtils.copyURLToFile(new URL(encodedS3Url), new File(localFilePath));

			        	    // Provide the local file path to the file input element
			        	    fileInput.sendKeys(localFilePath);

			        	    // Delete the downloaded file
			        	    File downloadedFile = new File(localFilePath);
			        	    if (downloadedFile.exists()) {
			        	        downloadedFile.delete();
			        	        System.out.println("Downloaded file deleted successfully.");
			        	    }  
			        	} catch (IOException e) {
			        	    System.out.println("Not work");
			        	    e.printStackTrace();
			        	}   
				       	   
			           }
			           else if(Previous_Year_of_Current_Course.equals("None")) {
			        	     
			           Select Previes_Year  = new Select(driver.findElement(By.id("noOfYrsCompleted")));
			           Previes_Year.selectByVisibleText(Previous_Year_of_Current_Course);
			           
			           } 	          
		  	           }
		            
		            	
		            	
		            	
		            
  
		            
		            
		            
		            
		            
		            
	            
	            
	            
	            
	            
        
 //Counter:				    
		    scriptRunCounter++;
        System.out.println("Script run count: " + scriptRunCounter);        
	            
        
		        }
        
	}

}
