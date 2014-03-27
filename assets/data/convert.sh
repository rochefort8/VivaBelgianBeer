
cd SJIS

# Check
check_result="ok" 

for bn in $(cat db.csv | cut -d, -f2)
do
    if [ ! -f ../$bn.jpg ] ; then
	echo "file $bn.jpg does not exist."  
	check_result="no" 
    fi
    if [ ! -f ./$bn.txt ] ; then
	echo "file $bn.txt does not exist."  
	check_result="no" 
    fi
done

if [ "$check_result" != "ok" ]; then
    echo "Some files are missing, see log." 
    exit 1 ;
fi

echo "OK, converting to Unicode..."

nkf -w db.csv > ../db.csv

for f in *.txt 
do
    nkf -w $f > ../$f
done

echo "Done."

exit 0



