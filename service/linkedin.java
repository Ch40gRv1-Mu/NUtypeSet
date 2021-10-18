public String Login() {
        // TODO Auto-generated method stub
        String pwd = "XXXXXXXXXXXXXXX";//密码
        String account = "XXXXXXXXXXXXXXXXXXXX";//账号
        String nameId="";
        String csrf_token="";
        Map<String, String> cookie = new HashMap<String,String>();
        Map<String, String> cookies = new HashMap<String,String>();
        try {
            String url="https://www.linkedin.com/login?trk=guest_homepage-basic_nav-header-signin";
            Connection connect = Jsoup.connect(url);
            connect.header("authority","www.linkedin.com");
            connect.header("cache-control","max-age=0,no-cache");
            connect.header("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
            connect.header("accept","text/html,appli cation/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            connect.header("referer","https://www.baidu.com/s?ie=·-8&wd=%E9%A2%86%E8%8B%B1");
            connect.header("accept-encoding","gzip, deflate, br");
            connect.header("accept-language","en-US;q=0.8,en;q=0.7");
 
 
            Connection.Response response = connect.ignoreContentType(true)
                    .execute();
            Document parse = response.parse();
            String loginCsrfParam = parse.select("input[name=loginCsrfParam]").get(0).attr("value");
            cookie = response.cookies();
            System.out.println(cookie);
            for (String s : cookie.keySet()) {
                if(s.equalsIgnoreCase("JSESSIONID")){
                    csrf_token=cookie.get(s);
                    System.out.println(csrf_token);
                    break;
                }
            }
 
 
            Map<String, String> datas = new HashMap<String,String>();
            datas.put("session_key", account);
            datas.put("session_password",pwd );
            datas.put("isJsEnabled", "false");
            datas.put("loginCsrfParam",loginCsrfParam);
            datas.put("fp_data", "default");
            datas.put("undefined", "");
            url="https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME";
            Connection connection2 = Jsoup.connect(url);
            connection2.header("authority","www.linkedin.com");
            connection2.header("cache-control","max-age=0,no-cache");
            connection2.header("origin","https://www.linkedin.com");
            connection2.header("upgrade-insecure-requests","1");
            connection2.header("content-type","application/x-www-form-urlencoded");
            connection2.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
            connection2.header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            connection2.header("referer","https://www.linkedin.com/");
            connection2.header("accept-encoding","gzip, deflate, br");
            connection2.header("accept-language","en-US;q=0.8,en;q=0.7");
            connection2.cookies(cookie);
            Connection.Response execute = connection2
                    .data(datas)
                    .cookies(cookie)
                    .method(Method.POST)
                    .execute();
            URL referer = execute.url();
            Map<String, String> executeCookie = execute.cookies();
            Document parse1 = execute.parse();
            String result=parse1.toString();
            if (result.contains("Sign-In Verification") || result.contains("verification code")) {
                cookie.putAll(executeCookie);
                cookies =cookie;
            }else{
                cookies =executeCookie;
            }
            nameId = RegexUtil.getValue("\"publicIdentifier\":\"(.*?)\"", result, 1).trim();
            System.out.println("登陆成功");
            return "success";
        } catch (Exception e) {
            System.out.println("登陆异常");
            return "error";
        }
 
    }


public class Linkedin { 

  public static void main(String[] args){ 
    try { 
      
      String shpath="sh /Users/mcr/Desktop/NUS/orbital/orbital_github/service/note.sh"; 
      Process ps = Runtime.getRuntime().exec(shpath); 
      ps.waitFor(); 
  
      BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream())); 
      StringBuffer sb = new StringBuffer(); 
      String line; 
      while ((line = br.readLine()) != null) { 
        sb.append(line).append("\n"); 
      } 
      String result = sb.toString(); 
      System.out.println(result); 
      }  
    catch (Exception e) { 
      e.printStackTrace(); 
      } 
  } 
} 

