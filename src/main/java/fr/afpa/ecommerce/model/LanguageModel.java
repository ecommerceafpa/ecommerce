package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Language;
import fr.afpa.ecommerce.jdbc.ConnectionFactory;
import fr.afpa.ecommerce.jdbc.ConnectionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Riad YOUSFI
 */
public class LanguageModel {

    public List<Language> findAll() throws IOException, SQLException, ClassNotFoundException {
        List<Language> languages = new ArrayList<>();
        String req = "SELECT id, name FROM language";
        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Language language = new Language();
            language.setId(rs.getInt("id"));
            language.setName(rs.getString("name"));
            languages.add(language);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return languages;
    }

}
