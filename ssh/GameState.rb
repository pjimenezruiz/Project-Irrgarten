#encoding:utf-8

module Irrgarten

	class GameState
		
		public 
			def initialize (l, p, m, cP, w, lo)
				@labyrinth =l
				@players =p
				@monsters =m
				@current_player= cP
				@winner =w
				@log =lo
			end
			
			attr_reader :labyrinth, :players, :monsters, :current_player, :winner, :log #Sirve para cerear instancias que se puedan leer pero no acceder desde fuera de la clase
			
			def get_labyrinth
				return @labyrinth
			end
			
			def get_players
				return @players
			end
			
			def get_monsters
				return @monsters
			end
			
			def get_current_player
				return @current_player
			end
			
			def get_winner
				return @winner
			end
			
			def get_log
				return @log
			end
	end
end
		
