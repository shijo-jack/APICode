package com.fresco.codelab.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresco.codelab.model.CodeLabRepo;
import com.fresco.codelab.model.CodeLabUser;
import com.fresco.codelab.repo.CodeLabRepoRepository;
import com.fresco.codelab.repo.CodeLabUserRepository;

@Service
@Transactional
public class DashboardService {
	
	@Autowired
	CodeLabUserRepository codeLabUserRepo;
	
	@Autowired
	CodeLabRepoRepository codeLabRepoRepo;
	
	
	public void save(CodeLabUser codeLabUser) {
		codeLabUserRepo.save(codeLabUser);
	}
	
	public CodeLabUser getCodeLabUser(String name, String password) {
		List<CodeLabUser> codeLabUserList = new ArrayList<CodeLabUser>();
		CodeLabUser labUser = null;
		codeLabUserList = codeLabUserRepo.findAll();
		for(CodeLabUser user: codeLabUserList) {
			if(name.equals(user.getUsername()) && password.equals(user.getPassword())) {
				labUser = user;
				break;
			}
		}
		return labUser;
	}
	
	public void save(CodeLabRepo codeLabRepo) {
		codeLabRepoRepo.save(codeLabRepo);
	}
	
	public List<CodeLabRepo> getCodeLabRepoWithOwnerId(Long ownerId){
		System.out.println("in");
		List<CodeLabRepo> codeLabRepoListFinal = new ArrayList<CodeLabRepo>();
		List<CodeLabRepo> codeLabRepoList = codeLabRepoRepo.findAll();
		System.out.println("in: "+codeLabRepoList.toString());
		for(CodeLabRepo repo: codeLabRepoList) {
			if(ownerId.equals(repo.getRepoOwnerId())) {
				codeLabRepoListFinal.add(repo);
			}
		}
		System.out.println("in: "+codeLabRepoList.size());
		return codeLabRepoList;
	}

	public List<CodeLabRepo> getAllCodeLabRepo(){
		List<CodeLabRepo> codeLabRepoList = codeLabRepoRepo.findAll();
		return codeLabRepoList;
	}
	
	public List<CodeLabUser> getCodeLabUserWithUserId(Long userAutoGenId) {
		Optional<CodeLabUser> codeLabUser = codeLabUserRepo.findById(userAutoGenId);
		return codeLabUser.map(value -> new ArrayList<CodeLabUser>(Arrays.asList(value)))
			    .orElseGet(() -> new ArrayList<CodeLabUser>());
	}
	
	public List<CodeLabRepo> getCodeLabRepoWithRepoId(Long repoId) {
		Optional<CodeLabRepo> codeLabRepo = codeLabRepoRepo.findById(repoId);
		List<CodeLabRepo> codeLabRepoList = codeLabRepo.map(value -> new ArrayList<CodeLabRepo>(Arrays.asList(value)))
			    .orElseGet(() -> new ArrayList<CodeLabRepo>());
		if(codeLabRepoList!=null && !codeLabRepoList.isEmpty()) {
			return codeLabRepoList;
		}else {
			return new ArrayList<CodeLabRepo>();
		}
	}
}
