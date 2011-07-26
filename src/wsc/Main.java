package wsc;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;


public class Main {

	static final String uName = "rupton@dev.force.com";
	static final String passwd = "Dfydpw10&";
	static EnterpriseConnection connection;
	static QueryResult result;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws ConnectionException {
		// TODO Auto-generated method stub
		ConnectorConfig config = new ConnectorConfig();
		config.setUsername(uName);
		config.setPassword(passwd);
		connection = Connector.newConnection(config);
		result = connection.query("SELECT ID, FIRSTNAME, LASTNAME, Account.Name" +
				" from Contact where accountid != null ORDER BY CreatedDate DESC Limit 5");
		if(result.getSize() > 0){
			for(int i = 0; i < result.getRecords().length; i++){
				Contact c = (Contact)result.getRecords()[i];
				System.out.println(c.getId() + "| " + c.getFirstName() + " " + c.getLastName() +
						" Belongs to account: " + c.getAccount().getName());
			}
		}
		
	}

}
