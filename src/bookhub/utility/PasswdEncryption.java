package bookhub.utility;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswdEncryption {     
	    public static void main(String[] args) throws NoSuchAlgorithmException {
	        String passwordToHash = "hemant";
	       
	        String securePassword = encrypt(passwordToHash);
	        System.out.println(securePassword);
	         
	       
	    }
	 
	    public static String encrypt(String passwordToHash)
	    {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-1");
	            
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }
	     
}