</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        var bool = false;
        $("div.header").click(function(){
            if(bool ==false) {
                $(this).css("background-color", "black");
                $("h1.headerh1").css("color", "White");
                bool=true;
            }
            else{
                $(this).css("background-color", "white");
                $("h1.headerh1").css("color", "black");
                bool=false;
            }
        });
    });



</script>
</body>
</html>
