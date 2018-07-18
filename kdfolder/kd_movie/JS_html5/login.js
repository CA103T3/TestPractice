function saveSomething(){
    var mail = document.getElementById("m").value;
    var psw = document.getElementById("p").value;
    console.log("要儲存的帳號:" , mail);
    console.log("要儲存的密碼:" , psw);
    window.localStorage.email =mail;
    window.sessionStorage.password =psw;
}

function loadSomething(){
    console.log("讀取的帳號:",window.localStorage.email);
    console.log("讀取的密碼:",window.localStorage.password);
        if(window.localStorage.email !== undefined){
            document.getElementById("m").value=window.localStorage.email;
        }
        if(window.localStorage.email !== undefined){
            document.getElementById("p").value=window.sessionStorage.password;
        }
}

//為字串新增trim方法,使用正規運算式擷取空格
//String.prototype.trim =function()
//{
//    return this.replace(/^\s*/,"").replace(/\s*$/,"");
//}
//
//function check(){
//    var form = document.form[0];
//    
//    var errStr = "";
//    
//    if(form.mail.value.trim() == ""){
//        errStr += "\n使用者名稱不能為空!";
//        form.mail.focus();
//    }
//    
//    if (form.psw.value == null || form.psw.value.trim() == ""){
//        errStr += "\n密碼不能為空!";
//        form.psw.focus();
//    }
//    
//    if(errStr != ""){
//        alert(errStr);
//    }else{
//        form.submit();
//    }
//    
//}
//document.getElementById("regist").onclick = check;

