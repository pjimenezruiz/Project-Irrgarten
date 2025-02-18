#encoding:utf-8

require_relative 'Player'

module Irrgarten

	class FuzzyPlayer < Player
	
		protected
			
			def defensive_energy
				return super
			end
			
		public
			
			def initialize (o)
				copy(o)
			end
			
			def move(directions, val_moves)
				pref_direction=super(directions,val_moves)
				return Dice::next_step(pref_direction,val_moves,get_intelligence)
			end
			
			def attack
				return super
			end
			
			def to_s
				return "Fuzzy" + super 
			end
			
	end
end
