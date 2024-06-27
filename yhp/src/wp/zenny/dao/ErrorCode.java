package wp.zenny.dao;

public class ErrorCode {

	public ErrorCode() {
	}
	public static String getErrorMessage(String errorCode) {
		String message = "Error's Info is not found";
		int code = 0;
		try {
		code = Integer.parseInt(errorCode);
		} catch(Exception e) {
			return message;
		}
		switch(code) {
		case 105:
			message = "Username đã tồn tại. Vui lòng kiểm tra lại!";
			break;
		case 248:
			message = "Đã xảy ra lỗi trong quá trình thêm. Vui lòng kiểm tra dữ liệu nhập!";
			break;
		case 369:
			message = "Đã xảy ra lỗi trong quá trình xóa";
			break;
		case 481:
			message = "Đã xảy ra lỗi trong quá trình cập nhật. Vui lòng kiểm tra và thử lại!";
			break;
		case 632:
			message = "Dữ liệu nhập vào chưa đúng. Vui lòng kiểm tra lại!";
			break;
		case 404:
			message = "Bạn đang cố gắng truy cập vào địa chỉ không tồn tại!";
			break;
		case 500:
			message = "System error, please comeback later!";
			break;
		}
		return message;
	}

}
