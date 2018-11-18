package project.common.module.entrytypesupporter;

import zebra.data.ParamEntity;

public interface EntryTypeSupporterBiz {
	public ParamEntity getIncomeTypeForSelectbox(ParamEntity paramEntity) throws Exception;
	public ParamEntity getExpenseMainTypeForSelectbox(ParamEntity paramEntity) throws Exception;
	public ParamEntity getExpenseSubTypeForSelectbox(ParamEntity paramEntity) throws Exception;
	public ParamEntity getExpenseTypesForContextMenu(ParamEntity paramEntity) throws Exception;
	public ParamEntity getAssetTypeForSelectbox(ParamEntity paramEntity) throws Exception;
	public ParamEntity getAssetTypeForContextMenu(ParamEntity paramEntity) throws Exception;
	public ParamEntity getRepaymentTypeForSelectbox(ParamEntity paramEntity) throws Exception;
	public ParamEntity getRepaymentTypeForContextMenu(ParamEntity paramEntity) throws Exception;
	public ParamEntity getBorrowingTypeForSelectbox(ParamEntity paramEntity) throws Exception;
	public ParamEntity getBorrowingTypeForContextMenu(ParamEntity paramEntity) throws Exception;
	public ParamEntity getLendingTypeForSelectbox(ParamEntity paramEntity) throws Exception;
	public ParamEntity getLendingTypeForContextMenu(ParamEntity paramEntity) throws Exception;
}