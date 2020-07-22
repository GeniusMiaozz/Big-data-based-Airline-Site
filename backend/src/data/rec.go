package data

type Hotel struct{
	Id        int64
	HOTEL     string
	LOCATION  string
	CLASS     string
	AVG_PRICE int64
	COUNTER   int64
	Userid    string
}
type Ticket struct{
	Id        int64
	Userid	  string
	FLIGHT_NO string
	Price     int64
	Date      string
	DEP_CT    string
	ARR_CT    string
	TYPE      int64
}
type Site struct{
	Id             int64
	UserId		   string
	ATTRACTION     string
	CLICK_COUNT    int64
	LOCATION       string
	AVG_PRICE      int64
}
type Travel struct{
	Id             int64
	MENU		   string
	GUIDE          string
	TYPE           string
	MONTH          int64
}