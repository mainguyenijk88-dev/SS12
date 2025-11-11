
let numberrow= 7;
let numbercol =12;

for( let i=1; i<=numberrow; i++){
    let result= "";
    for( let j=1; j<=numbercol; j++){
        
        if ( i==1||i==numberrow||j==1||j==numbercol){
            result += "*"
        }
        else{
            result += " "
        } 
    }
    console.log(result);
    
}