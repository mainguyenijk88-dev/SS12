

let a =  +(prompt("moi ban nhap gia tri a"))
let b = +(prompt("moi ban nhap gia tri b"))
let c = +(prompt("moi ban nhap gia tri c"))

let delta= (b**2)-(4*a*c)
let x1= (-b+Math.sqrt(delta))/(2*a)
let x2= (-b-Math.sqrt(delta))/(2*a)
let x = -(b/2*a)


if (delta>0)
    {
console.log(`co 2 nghiem phan biet x1= ${x1};x2= ${x2}`);

} else if(delta ===0){

console.log(`co 1 nghiem kep ;x = ${x}`);
    
}else if( delta<0){
console.log(`pt vo nghiem ` );
    }

