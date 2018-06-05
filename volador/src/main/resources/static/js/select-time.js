$.fn.selectDate = function(usery,userm,userd){
	console.log(usery,userm,userd)
			var minYear = 1970
			var maxYear = (new Date).getFullYear()
			var yearSel = document.getElementById('year')
			var monthSel = document.getElementById('month')
			var daySel = document.getElementById('day')
	
			for(var y = maxYear;y >= minYear;y--){
				var yearOpt = document.createElement('option')
				yearOpt.value = y
				if (parseInt(usery)==parseInt(y)) {
					yearOpt.setAttribute("selected","selected")
				}
				yearOpt.innerHTML = y+'年'
				yearSel.appendChild(yearOpt)
			}

			// $("#year").click(function(event){
				if(!$("#year option:selected").val()) return;
				removeOption(monthSel)
				addOption(12,'月',monthSel,userm)
				removeOption(daySel)
			// })

			// $("#month").click(function(){
				removeOption(daySel)	
				var year = $("#year option:selected").val()
				var month = $("#month option:selected").val()
				if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
					addOption(31,'日',daySel,userd)
				}else if(month==4 || month==6 || month==9 || month==11){
					addOption(30,'日',daySel,userd)
				}else if(month==2){
					if((year%4 == 0 && year%100 != 0 ) || (year%400 == 0)){
						addOption(29,'日',daySel,userd)
					}else{	
						addOption(28,'日',daySel,userd)
					}
				}
			// })

			function addOption(num,unit,parent,user){
				//num：选项个数
				//unit：单位（年/月/日）
				//parent：父对象
				for(var index=1;index <= num;index++){
					var opt =document.createElement('option')
					$(opt).attr('value',index)
					if (parseInt(user)==parseInt(index)) {
						$(opt).attr("selected","selected")
					}
					if(index<10){index = '0'+index}
					$(opt).html(index+unit)
					$(parent).append(opt)
				}
			}
			
			function removeOption(parent){
				//parent：父对象
				var options = $(parent).find('option')
				for(var index = 1;index < options.length;index++){
					parent.removeChild(options[index])
				}
			}
		}