
let integer1 = +(prompt("moi ban nhap vao so nguyen dau tien"))
let integer2 = +(prompt("moi ban nhap vao  so nguyen thu 2"))
let integer3 = +(prompt("moi ban nhap vao so nguyen thu 3"))
if ( integer1>integer2&& integer1>integer3){
    alert("so nguyen dau tien la lon nhat")
}
else if( integer2>integer1&& integer2>integer3){
    alert("so nguyen thu 2 la lon nhat")
}
else if( integer3>integer1&& integer3>integer2){
    alert("so nguyen thu 3 la lon nhat")
}
else{
    alert("so sanh ko xac dinh duoc")
}