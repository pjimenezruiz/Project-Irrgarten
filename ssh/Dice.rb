#encoding:utf-8

module Irrgarten

	class Dice
		private
			@@MAX_USES=5
			@@MAX_INTELLIGENCE=10.0
			@@MAX_STRENGTH=10.0
			@@RESURRECT_PROB=0.3
			@@WEAPONS_REWARD=2
			@@SHIELDS_REWARD=3
			@@HEALTH_REWARD=5
			@@MAX_ATTACK=3
			@@MAX_SHIELD=2
		
			@@generator=Random.new 
		
		public	# REVISAR RAND CON ENTEROS Y FLOAT
			def self.random_pos(max)
				return @@generator.rand(max)
			end
			
			def self.who_starts(nplayers)
				return @@generator.rand(nplayers)
			end
			
			def self.random_intelligence
				return @@generator.rand() * @@MAX_INTELLIGENCE
			end
			
			def self.random_strength
				return @@generator.rand() * @@MAX_STRENGTH
			end
			
			def self.resurrect_player
				return @@generator.rand() < @@RESURRECT_PROB
			end
			
			def self.weapons_reward
				return @@generator.rand(@@WEAPONS_REWARD + 1)
			end
			
			def self.shields_reward
				return @@generator.rand(@@SHIELDS_REWARD + 1)
			end
			
			def self.health_reward
         			return @@generator.rand(@@HEALTH_REWARD + 1)
			end
			
			def self.weapon_power
				return @@generator.rand() * @@MAX_ATTACK
			end
			
			def self.weapon_power
				return @@generator.rand() * @@MAX_ATTACK
			end
			
			def self.shield_power
				return @@generator.rand() * @@MAX_SHIELD
			end
			
			def self.uses_left
				return @@generator.rand(@@MAX_USES + 1)
			end
			
			def self.intensity(competence)
            			return @@generator.rand() * competence
        		end
			
			def self.discard_element(uses_left)
				probabilidad_discard = (@@MAX_USES - uses_left).to_f / @@MAX_USES
           			return @@generator.rand() < probabilidad_discard
			end
			
			def self.next_step(preference, valid_moves, intelligence)
				if @@generator.rand()<=(intelligence/@@MAX_INTELLIGENCE)
					return preference
				else 
					i=@@generator.rand(valid_moves.size-1)
					return valid_moves[i]
				end
			end
	end
end

