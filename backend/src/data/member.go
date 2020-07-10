package data

type Member_Register struct {
	Member_No int64  `r_db:"MEMBER_NO"`
	Telephone int64  `r_db:"TELEPHONE"`
	Password  string `r_db:"PASSWORD"`
}
