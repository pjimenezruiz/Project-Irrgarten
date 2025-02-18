#encoding:utf-8

module Irrgarten
	
	class LabyrinthCharacter
	
		protected
			
			def get_health
				return @health
			end
			
			def set_health(h)
				@health = h
			end
			
			def got_wounded
				@health-=1
			end
		
		public
			
			def initialize(n,i,s,h) 
				@name=n
				@intelligence=i
				@strength=s
				@health=h
				@row=nil
				@col=nil
			end
			
			def copy(o)  #PREGUNTAR
				@name = o.get_name
				@intelligence = o.get_intelligence
				@strength = o.get_strength
				@health = o.get_health
				@row = o.get_row
				@col = o.get_col
			end
			
			def dead
				if (@health==0)
					return true
				else
					return false
				end
			end
			
			def get_intelligence
				return @intelligence
			end
			
			def get_strength
				return @strength
			end
			
			def get_name
				return @name
			end
			
			def get_row
				return @row
			end
			
			def get_col
				return @col
			end
			
			def set_pos(r,c)
				@row=r
				@col=c
			end
			
			def to_s
				return "[[#{@name}, #{@intelligence}, #{@strength}, #{@health}, #{@row}, #{@col}]"
			end
	end
end
