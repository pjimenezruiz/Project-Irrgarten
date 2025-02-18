#encoding:utf-8

require_relative 'LabyrinthCharacter'

module Irrgarten
	
	class Monster < LabyrinthCharacter
		private
			@@INITIAL_HEALTH=5
		public
			def initialize(n,i,s) 
				super(n,i,s,@@INITIAL_HEALTH)
			end
			
			def attack
				return Dice.intensity(get_strength)
			end
			
			def defend(rA)
				is_dead=dead()
				if !is_dead
					defensive_energy=Dice.intensity(get_intelligence)
					if defensive_energy<rA
						got_wounded()
						is_dead=dead()
					end
				end
				
				return is_dead
			end
			
			def to_s
				return "M"+super 
			end
	end
end
			
			
