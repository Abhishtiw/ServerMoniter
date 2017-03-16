function validateSheet() {

	/*
	 * initially falg is set to true
	 */
	flag = true;

	var path = document.app_excel_Form.filepath.value;

	if (path == "") {
		document.getElementById('appNameError').innerHTML = "File path cannot be empty! Please provide .xlsx Extension";
		flag = false;
	}
	return flag;
}