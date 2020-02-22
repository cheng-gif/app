package cn.appsys.service.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.datadictionary.DataDictionaryMapper;
import cn.appsys.pojo.DataDictionary;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService{
	@Autowired
	private DataDictionaryMapper dataDictionaryMapper;
	
	@Override
	public List<DataDictionary> getDataDictionaryList(String typeCode) {
		return dataDictionaryMapper.getDataDictionaryList(typeCode);
	}

}
