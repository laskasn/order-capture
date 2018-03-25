package gr.laskarisn.entities.customtypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.AssertionFailure;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

public class OrderStatusEnumType extends org.hibernate.type.EnumType {

	
	private static final long serialVersionUID = 3219608241778441311L;

	
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if(value == null)
            st.setNull( index, Types.OTHER );
        else 
            st.setObject(index, value.toString(), Types.OTHER);
    }
	
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws SQLException {
		if(names.length>0)
			return OrderStatus.valueOf((String)rs.getObject(names[0]));
		else
			return null;
    }
	
	
}
