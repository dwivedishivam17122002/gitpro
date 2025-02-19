package ecom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class passwordUtil {


		public static boolean hashPassword(String password,String storedHash) {
			 try {
		            MessageDigest md = MessageDigest.getInstance("SHA-256");
		            byte[] hash = md.digest(password.getBytes());
		            StringBuilder hexString = new StringBuilder();
		            for (byte b : hash) {
		                hexString.append(String.format("%02x", b));
		            }
		            return hexString.toString().equals(storedHash);
		        } catch (NoSuchAlgorithmException e) {
		            throw new RuntimeException(e);
		        }
		}



}
