let first =0;
let second=1;
console.log(first);
console.log(second);


for(let i=1; i<=18; i=i+1){
    let next= first+second;
    console.log(`${next} (${i+2})`); 
    first=second;
    second=next;

    
    
}