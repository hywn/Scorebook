import java.sql.ResultSet;

public abstract class Location extends IDStorable { // something that has an address

	private static final String COLUMN_ADDRESS_ID = "aid"; // column names

	public Location(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public Location(StorableStruct struct, String addressID, Object... values) {

		super(struct, values);

		register(COLUMN_ADDRESS_ID, addressID);

	}

	public String getAddressID() { return getValue(COLUMN_ADDRESS_ID); }

	public Address getAddress(Database db) { return db.getAddress(getAddressID()); }

}
