package com.example.rmb;


import com.example.rmb.entity.User;


public class Setting {

	public static int TIME_OUT = 20000;
	public static String DATA = "data";
	public static String PHONE_NUMBER = "username";
	public static String PASSWORD = "password";
	public static User USER;
	public static String ROOT_PATH = "PocketCampus/";
	public static String SELL_PATH = "SellCache/";
	public static String PHOTO_PATH = "PhotoCache/";
	public static String SHARE_PATH = "ShareCache/";
	public static String PHOTO_NAME = "photo.png";
	public static String SHARE_IMG_NAME = "shareimg.jpg";
	public static String GOODS_IMG_NAME_BASE = "goods_%d.jpg";
	public static int PAGE_SIZE = 6;
	
	public static String HOST = "http://42.121.131.145:8080";
	//public static String HOST = "http://192.168.2.104:8080";
	
	//APIs
	public static String GET_APP = HOST + "/MobileSchoolServer/dlapk";
	public static String LOGIN_URL = HOST +"/MobileSchoolServer/pc_userLoginAction";
	public static String REGIST_URL = HOST + "/MobileSchoolServer/pc_userRegistAction";
	public static String GET_QUESTION_URL = HOST + "/MobileSchoolServer/pc_userGetQuestion";
	public static String GET_PASSWORD_URL = HOST + "/MobileSchoolServer/pc_userGetPassword";
	public static String SET_PASSWORD_URL = HOST + "/MobileSchoolServer/pc_userPwdUpdateAction";
	public static String SET_USERINFO_URL = HOST + "/MobileSchoolServer/pc_userUpdateAction";
	public static String GET_NOTICE_INFO_LIST_URL = HOST + "/MobileSchoolServer/pc_publicInformationAction";
	public static String GET_NOTICE_INFO_CONTENT_URL = HOST + "/MobileSchoolServer/pc_publicInformationDetailAction";
	public static String GET_BLESSING_LIST_URL = HOST + "/MobileSchoolServer/pc_BlessingMessageListAction";
	public static String GET_BLESSING_INFO_URL = HOST + "/MobileSchoolServer/pc_BlessingMessageDetailAction";
	public static String SEND_BLESSING_URL = HOST + "/MobileSchoolServer/pc_BlessingMessageSendAction";
	public static String GET_BLESSING_MESSAGE_URL = HOST + "/MobileSchoolServer/pc_BlessingMessagePullAction";
	public static String SEND_ASK_URL = HOST + "/MobileSchoolServer/pc_HelpQuestionSendAction";
	public static String GET_HELP_LIST_URL = HOST + "/MobileSchoolServer/pc_HelpQuestionListAction";
	public static String GET_SELF_HELP_LIST_URL = HOST +"/MobileSchoolServer/pc_HelpMyQuestionListAction";
	public static String GET_ANSWER_LIST_URL = HOST + "/MobileSchoolServer/pc_HelpAnwserListAction";
	public static String LOVE_ANSWER_URL = HOST + "/MobileSchoolServer/pc_HelpAnwserAgreeAction";
	public static String SET_ANSWER_OK = HOST +"/MobileSchoolServer/pc_HelpAnwserAcceptAction";
	public static String SET_USER_LEAVE_MSG = HOST + "/MobileSchoolServer/pc_HelpMakeAnswerAction";
	public static String GET_SHARE_HOME_LIST = HOST + "/MobileSchoolServer/pc_ShareMessageListAction";
	public static String GET_SHARE_MSG_LIST = HOST + "/MobileSchoolServer/pc_ShareCommentListAction";
	public static String SHARE_MOOD_URL = HOST + "/MobileSchoolServer/pc_ShareMessageSendShareAction";
	public static String SET_SHARE_LOVE_URL = HOST + "/MobileSchoolServer/pc_ShareMessageAgreeAction";
	public static String GET_SHARE_OF_USER_LIST = HOST + "/MobileSchoolServer/pc_ShareMessageUsersListAction";
	public static String GET_SHARE_LEAVE_MSG_LIST = HOST + "/MobileSchoolServer/pc_ShareMessageCommentListAction";
	public static String SET_SHARE_LEAVE_MSG = HOST + "/MobileSchoolServer/pc_ShareMessageSendAction";
	public static String GET_USER_INFO_URL = HOST + "/MobileSchoolServer/pc_ShareMessageUserInfoAction";
	public static String SET_LOVE_PHONE_URL = HOST + "/MobileSchoolServer/pc_SecretLoveAddAction";
	public static String GET_LOVE_LIST_URL = HOST + "/MobileSchoolServer/pc_SecretMessageListAction";
	public static String GET_PULL_LOVE_URL = HOST + "/MobileSchoolServer/pc_SecretMessagePullAction";
	public static String GET_SELL_LIST_URL = HOST + "/MobileSchoolServer/pc_MarketSaleListAction";
	public static String GET_BUY_LIST_URL = HOST + "/MobileSchoolServer/pc_MarketBuyListAction";
	public static String GET_ME_SELL_LIST_URL = HOST + "/MobileSchoolServer/pc_MarketSaleUserListAction";
	public static String GET_ME_BUY_LIST_URL = HOST + "/MobileSchoolServer/pc_MarketBuyUserListAction";
	public static String DELETE_GOOODS_URL = HOST + "/MobileSchoolServer/pc_SaleMarketDelAction";
	public static String DELETE_BUY_URL = HOST + "/MobileSchoolServer/pc_BuyMarketDelAction";
	public static String SEARCH_GOODS = HOST + "/MobileSchoolServer/pc_MarketSaleQueryListAction";
	public static String SEARCH_BUY = HOST + "/MobileSchoolServer/pc_MarketBuyQueryListAction";
	public static String ADD_GOODS_URL = HOST + "/MobileSchoolServer/pc_MarketSaleAddAction";
	public static String ADD_BUY_URL = HOST + "/MobileSchoolServer/pc_MarketBuytAddAction";
	public static String GET_LEAVE_MSG_GOODS = HOST + "/MobileSchoolServer/pc_SaleCommentListAction";
	public static String GET_LEAVE_MSG_BUY = HOST + "/MobileSchoolServer/pc_BuyCommentListAction";
	public static String SET_SELL_LEAVE_MSG = HOST + "/MobileSchoolServer/pc_SaleCommentSendAction";
	public static String SET_BUY_LEAVE_MSG = HOST + "/MobileSchoolServer/pc_BuyCommentSendAction";
	public static String CHECK_VERSION = HOST + "/MobileSchoolServer/pc_VersionCheckAction";
	public static String SET_ADVICE_URL = HOST + "/MobileSchoolServer/pc_AdviseSendAction";
	
}
