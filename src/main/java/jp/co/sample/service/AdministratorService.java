package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository repository;

	// 管理者情報の挿入
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}

	// ログイン
	public Administrator login(String mailAddress, String password) {
		Administrator administrator = repository.findByMailAddressAndPassWord(mailAddress, password);
		return administrator;
	}

}
