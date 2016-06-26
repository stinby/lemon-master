package com.mossle.internal.store.service;

import java.util.Date;

import javax.activation.DataSource;

import javax.annotation.Resource;

import com.mossle.api.store.StoreDTO;

import com.mossle.internal.store.persistence.domain.StoreInfo;
import com.mossle.internal.store.persistence.manager.StoreInfoManager;

import com.mossle.spi.store.InternalStoreConnector;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StoreService {
    private StoreInfoManager storeInfoManager;
    private InternalStoreConnector internalStoreConnector;

    private void saveStoreInfo(String model,DataSource dataSource,StoreDTO storeDto,String tenantId){
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setName(dataSource.getName());
        storeInfo.setModel(model);
        storeInfo.setPath(storeDto.getKey());
        storeInfo.setCreateTime(new Date());
        storeInfo.setTenantId(tenantId);
        storeInfoManager.save(storeInfo);
    }
    private StoreDTO saveStoreInfo_StoreDTO(String model,DataSource dataSource,String key,String tenantId,int view){
        StoreDTO storeDto=null;
		try {
			if(view==0)
				storeDto = this.internalStoreConnector.saveStore(model, key,dataSource, tenantId);
			else
				storeDto = this.internalStoreConnector.saveStore(model,dataSource, tenantId);
			saveStoreInfo(model,dataSource,storeDto,tenantId);
		} catch (Exception e) {}
        return storeDto;
    }
    public StoreDTO saveStore(String model, String key, DataSource dataSource,String tenantId) throws Exception {
    		return saveStoreInfo_StoreDTO(model, dataSource, key, tenantId, 0);
    }

    public StoreDTO saveStore(String model, DataSource dataSource,String tenantId) throws Exception {
    		return saveStoreInfo_StoreDTO(model, dataSource, "", tenantId, 1);  
    }

    @Resource
    public void setInternalStoreConnector(InternalStoreConnector internalStoreConnector) {
        this.internalStoreConnector = internalStoreConnector;
    }

    @Resource
    public void setStoreInfoManager(StoreInfoManager storeInfoManager) {
        this.storeInfoManager = storeInfoManager;
    }
}
