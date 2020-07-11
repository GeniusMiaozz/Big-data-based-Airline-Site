package airline

import (
	"Airline/data"
	"Airline/data/r_db"
)

/*
查票逻辑
返回码：0-正常；1-内部错误；2-逻辑错误
*/
func SearchForFlight(info *data.Flight_Query) (int, []data.Flight_Query, error) {
	db, _ := r_db.Connect()
	rows, err := db.Query([]string{"*"}, []string{"Flight_Query"},
		"DEP_CT='"+info.Dep_Ct+"' and ARR_CT='"+info.Arr_Ct+"' and DATE='"+info.Date+"'")
	defer rows.Close()

	if err != nil {
		return 1, nil, err
	}

	var fql []data.Flight_Query
	for rows.Next() {
		var fq data.Flight_Query
		err = rows.Scan(
			&fq.Company,
			&fq.Flight_No,
			&fq.Date,
			&fq.Dep_Ct,
			&fq.Dep_Airport,
			&fq.Arr_Ct,
			&fq.Arr_Airport,
			&fq.Dep_Terminal,
			&fq.Arr_Terminal,
			&fq.Dep_Scheduled,
			&fq.Arr_Scheduled,
			&fq.E_Price,
			&fq.E_Tickets,
			&fq.E_Discount,
			&fq.S_Price,
			&fq.S_Tickets,
			&fq.S_Discount,
			&fq.F_Price,
			&fq.F_Tickets,
			&fq.F_Discount,
		)
		if err != nil {
			return 1, nil, err
		}
		fql = append(fql, fq)
	}
	return 0, fql, nil
}
