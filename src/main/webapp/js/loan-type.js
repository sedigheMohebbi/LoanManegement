function addGrant() {
    var table = document.getElementById("myTable");
    var name = document.getElementById("name");
    var minContractDuration = document.getElementById("minContractDuration");
    var maxContractDuration = document.getElementById("maxContractDuration");
    var minContractAmount = document.getElementById("minContractAmount");
    var maxContractAmount = document.getElementById("maxContractAmount");
    var rowCount = table.rows.length;
    if (validate(name, minContractDuration.value, maxContractDuration.value, minContractAmount.value, maxContractAmount.value)) {
        var row = table.insertRow(rowCount);

        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        cell1.innerHTML = '<input type="text" name="name" value="' + name.value + '" >';
        cell2.innerHTML = '<input type="text"  name="minContractDuration" value="' + minContractDuration.value + '">';
        cell3.innerHTML = '<input type="text" name="maxContractDuration" value="' + maxContractDuration.value + '">';
        cell4.innerHTML = '<input type="text" name="minContractAmount" value="' + minContractAmount.value + '">';
        cell5.innerHTML = '<input type="text" name="maxContractAmount" value="' + maxContractAmount.value + '">';
        name.value = "";
        minContractDuration.value = "";
        maxContractDuration.value = "";
        minContractAmount.value = "";
        maxContractAmount.value = "";
    }
}
function validate(name, minContractDuration, maxContractDuration, minContractAmount, maxContractAmount) {
    if (minContractDuration.value > maxContractDuration.value) {
        alert("Min Duration should be less than max duration ");
        return false;
    }

    if (minContractAmount.value > maxContractAmount.value) {
        alert("Min Amount should be less than max Amount ");
        return false;
    }
    if (name.value.length <= 0) {
        alert("please Enter Name");
        return false;
    }
    if (minContractDuration.length <= 0) {
        alert("please Enter Min Contact Duration");
        return false;
    }
    if(minContractDuration<0){
        alert("Min Contract Duration should be positive");
        return false;
    }
    if (maxContractAmount.length <= 0) {
        alert("please Enter Max Contact Duration");
        return false;
    }
    if(maxContractDuration<0){
        alert("Max Contract Duration should be positive");
        return false;
    }
    if (minContractAmount.length <= 0) {
        alert("please Enter Min Contact Amount");
        return false;
    }
    if(minContractAmount<0){
        alert("Min Contract Amount should be positive");
        return false;
    }
    if (maxContractAmount.length <= 0) {
        alert("please Enter Max Contact Amount");
        return false;
    }
    if(maxContractAmount<0){
        alert("Max Contract Amount should be positive");
        return false;
    }
    return true;

}
function addLoanType() {
    var name = document.getElementById("loanTypeName");
    var interestRate = document.getElementById("interestRate");
    if(name.value.trim().length<=0){
        alert("please Enter Name");
        return false;
    }
    if(interestRate.value.length<=0 ){
        alert("please Enter Max Contact Amount");
        return false;
    }
    if(interestRate.value<0){
        alert("Interest Rate should be positive");
        return false;
    }
    document.forms[0].submit();
}
