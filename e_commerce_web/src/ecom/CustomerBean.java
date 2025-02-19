package ecom;

public class CustomerBean {
           String name;
           String email;
           String password;
           int phone_no;
           String address;
           
           public CustomerBean() {
        	   
           }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getPhone_no() {
			return phone_no;
		}
		public void setPhone_no(int phone_no) {
			this.phone_no = phone_no;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public CustomerBean(String name, String email, String password, int phone_no,String address) {
        	   this.name=name;
        	   this.email=email;
        	   this.password=password;
        	   this.phone_no=phone_no;
        	   this.address=address;
           }
           
           
}
