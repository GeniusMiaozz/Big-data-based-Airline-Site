package identity

import (
	"Airline/data"
	"Airline/data/r_db"
	"Airline/utils"
	"database/sql"
	"errors"
	"strconv"
)

/*
注册逻辑
状态码：0-正常；1-内部错误；2-逻辑错误
*/
func SignUp(info *data.Member_Register) (int, error) {
	db, _ := r_db.Connect()
	rows, err := db.Query([]string{"*"}, []string{"Member_Register"},
		"TELEPHONE="+strconv.FormatInt(info.Telephone, 10))
	if err != nil {
		return 1, err
	}
	for rows.Next() {
		return 2, errors.New("该手机号已经被注册")
	}

	err = db.Insert("Member_Register", nil,
		[]string{strconv.FormatInt(utils.GenerateSnowFlakeId(), 10),
			strconv.FormatInt(info.Telephone, 10),
			info.Password})
	if err != nil {
		return 1, err
	}

	return 0, nil
}

/*
登录逻辑
状态码：0-正常；1-内部错误；2-逻辑错误
*/
func SignIn(info *data.Member_Register) (int, string, error) {
	db, _ := r_db.Connect()
	var rows *sql.Rows
	var err error
	rows, err = db.Query([]string{"MEMBER_NO", "PASSWORD"},
		[]string{"Member_Register"}, "TELEPHONE="+strconv.FormatInt(info.Telephone, 10))
	defer rows.Close()

	if err != nil {
		return 1, "", err
	}

	var member_no, password string
	for rows.Next() {
		rows.Scan(&member_no, &password)
		if password == info.Password {
			return 0, member_no, nil
		}
	}
	return 2, "", errors.New("密码不正确或帐号不存在")
}
