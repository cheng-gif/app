package cn.appsys.result;

public class Result {
	
	private String status;
	private String msg;
	private Object data;
	private Integer code;
	
	public static Result ok(){
		Result r = new Result();
		r.setMsg(null);
		r.setStatus("200");
		r.setData(null);
		return r;
	}
	
	
	public static Result ok(String msg){
		Result r = new Result();
		r.setMsg(msg);
		r.setStatus("200");
		r.setData(null);
		return r;
	}
	public static Result ok(Object data){
		Result r = new Result();
		r.setMsg(null);
		r.setStatus("200");
		r.setData(data);
		return r;
	}
	public static Result error(){
		Result r = new Result();
		r.setMsg(null);
		r.setStatus("500");
		r.setData(null);
		return r;
	}
	public static Result error(String msg){
		Result r = new Result();
		r.setMsg(msg);
		r.setStatus("500");
		r.setData(null);
		return r;
	}public static Result error(Object data){
		Result r = new Result();
		r.setMsg(null);
		r.setStatus("500");
		r.setData(data);
		return r;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	
	
}
