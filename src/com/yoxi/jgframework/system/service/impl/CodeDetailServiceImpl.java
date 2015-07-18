package com.yoxi.jgframework.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.jgframework.system.entity.TSAttachment;
import com.yoxi.jgframework.system.entity.TSCodeDetail;
import com.yoxi.jgframework.system.service.CodeDetailService;
import com.yoxi.jgframework.system.service.DeclareService;


@Service("codedetailService")
@Transactional
public class CodeDetailServiceImpl extends CommonServiceImpl implements CodeDetailService {

	/*
	 * 
	 * 对代码表拖动进行排序
	 * currentCodeDetail 当前拖动的行
	 * beforeCodeDetail 往前拖动时，拖到那条记录的前面
	 * backCodeDetail  往后拖动时，拖到那条记录的后面
	 * (non-Javadoc)
	 * @see com.yoxi.jgframework.system.service.CodeDetailService#orderCodeDetail(com.yoxi.jgframework.system.entity.TSCodeDetail, com.yoxi.jgframework.system.entity.TSCodeDetail, com.yoxi.jgframework.system.entity.TSCodeDetail)
	 */
	@Override
	public int orderCodeDetail(TSCodeDetail currentCodeDetail,
			TSCodeDetail beforeCodeDetail, TSCodeDetail backCodeDetail) {
		// TODO Auto-generated method stub
		if (currentCodeDetail == null)
			return 0;
		else if (beforeCodeDetail != null){
			int beforeCodeOrder = beforeCodeDetail.getCodeOrder();
			if (beforeCodeDetail.getCodeOrder().equals(currentCodeDetail.getCodeOrder()))
				beforeCodeOrder -=1;
			int i = this.updateBySqlString("update t_s_code_detail set codeOrder=" + beforeCodeOrder +
					" where id=" + currentCodeDetail.getId());
			int j = this.updateBySqlString("update t_s_code_detail set codeOrder=codeOrder+1" + 
					" where codeOrder>=" + beforeCodeOrder +
					" and  codeOrder <=" + currentCodeDetail.getCodeOrder() +
					" and id <>" + currentCodeDetail.getId() +
					" and typegroupid='" + currentCodeDetail.getTSCodeType().getId() + "'");
			return i +j;
		}else if (backCodeDetail != null){
			int backCodeOrder = backCodeDetail.getCodeOrder();
			if (backCodeDetail.getCodeOrder().equals(currentCodeDetail.getCodeOrder()))
				backCodeOrder +=1;
			int i = this.updateBySqlString("update t_s_code_detail set codeOrder=" + backCodeOrder +
					" where id=" + currentCodeDetail.getId());
			int j =  this.updateBySqlString("update t_s_code_detail set codeOrder=codeOrder-1" + 
					" where codeOrder<=" + backCodeOrder +
					" and codeOrder >=" + currentCodeDetail.getCodeOrder() +
					" and id <>" + currentCodeDetail.getId() + 
					" and typegroupid='" + currentCodeDetail.getTSCodeType().getId() + "'");
			return i +j;
		}
		else 
			return 0;		
		
	}

	
	
}
