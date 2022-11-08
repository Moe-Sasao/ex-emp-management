package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;
//administratorテーブルの操作用リポジトリ

@Repository
public class AdministratorRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	// Administratorオブジェクトの生成
	private static final RowMapper<Administrator> Administrator_ROW_MAPPER = (rs, i) -> {
		Administrator admin = new Administrator();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setMailAddress(rs.getString("mail_address"));
		admin.setPassword(rs.getString("password"));

		return admin;

	};

	// 管理者情報の挿入
	public void insert(Administrator admin) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(admin);
		String insertsql = "INSERT INTO administrators(name,mail_address,password) VALUES (:name,:mailAddress,:password)";
		template.update(insertsql, param);
	}

	// メールアドレス、パスワードから管理者情報取得
	public Administrator findByMailAddressAndPassWord(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress",mailAddress).addValue("password", password);
		List<Administrator> adminList = template.query(sql, param, Administrator_ROW_MAPPER);
		if (adminList.size() == 0) {
			return null;
		}
		return adminList.get(0);
	}

}
