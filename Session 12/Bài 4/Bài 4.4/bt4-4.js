
let a=0;
let b=1;
let c=a+b;


for(let i=1; i<=20; i=i+1){
    c=a+b ;
  a=b;
  b=c;
  if(c%5===0){
    console.log(c);
    break;
  }

}
