package poolweb.data.dao;

import poolweb.data.model.Poll;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;
import javax.sql.DataSource;
import java.sql.SQLException;

public class PoolWebDataLayer extends DataLayer {

    public PoolWebDataLayer(DataSource dataSource) throws SQLException {
        super(dataSource);
    }

    @Override
    public void init() throws DataException {
        registerDAO(User.class, new UserDAO_MySQL(this));
        registerDAO(Poll.class, new PollDAO_MySQL(this));
    }

    public UserDAO getUserDAO() { return ((UserDAO) getDAO(User.class)); }
    public PollDAO getPollDAO() { return ((PollDAO) getDAO(Poll.class)); }
}