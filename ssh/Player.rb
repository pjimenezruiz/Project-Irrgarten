#encoding:utf-8

require_relative 'LabyrinthCharacter'

module Irrgarten

	class Player < LabyrinthCharacter
	
		private
			@@MAX_WEAPONS=2
			@@MAX_SHIELDS=3
			@@INITIAL_HEALTH=1
			@@HITS2LOSE=3
			
			def receive_weapon(w)
				for w in @weapons do
					disc=w.discard()
					if disc
						@weapons.delete(w)
					end
				end
				size=@weapons.size()
				if size<@@MAX_WEAPONS
					@weapons.push(w)
				end
			end
			
			def receive_shield(s)
				for s in @shields do
					disc=s.discard
					if disc
						@shields.delete(s)
					end
				end
				size=@shields.size()
				if size<@@MAX_SHIELDS
					@shields.push(s)
				end
			end
			
			def new_weapon
				power=Dice.weapon_power()
				uses=Dice.uses_left
				return weapon=Weapon.new(power, uses)
			end
			
			def new_shield
				protection=Dice.shield_power()
				uses=Dice.uses_left
				return shield=Shield.new(protection, uses)
			end
			
			def sum_weapons
				suma = 0
				for i in 0..@weapons.size()-1
					suma += @weapons[i].attack
				end
				return suma
			end
			
			def sum_shields
				suma = 0
				for i in 0..@shields.size()-1
					suma += @shields[i].protect
				end
				return suma
			end
			
			def defensive_energy
				sum = sum_shields()
				if sum != nil
					return @intelligence + sum
				else
					return @intelligence
				end
			end
			
			def manage_hit(rA)
				defense=defensive_energy()
				if defense<rA
					got_wounded()
					inc_consecutive_hits()
				else
					reset_hits()
				end
				if @consecutive_hits==@@HITS2LOSE || dead()
					reset_hits()
					lose=true
				else
					lose=false
				end
				return lose
			end
			
			def reset_hits
				@consecutive_hits=0
			end
			
			def got_wounded
				@health-=1
			end
			
			def inc_consecutive_hits
				@consecutive_hits+=1
			end
			
		public
			def initialize(n,i,s)
			
				super("Player #{n}",i,s,@@INITIAL_HEALTH)
				@number=n;
				@consecutive_hits=0
				@weapons = []
				@shields = []
			end
			
			def copy(o)
				super(o)
				@number=o.get_number
				@consecutive_hits=0
				@weapons=o.get_weapons
				@shields=o.get_shields
			end
			
			def resurrect 
				@weapons.clear
				@shields.clear
				@health=@@INITIAL_HEALTH
				@consecutive_hits=0
			end
			
			def get_number
				return @number
			end
			
			def get_weapons 
				return @weapons
			end
			
			def get_shields
				return @shields
			end
			
			def move(d, vM)
				size=vM.size()
				contained=vM.include?(d)
				if size>0 && !contained
					return vM[0]
				else
					return d
				end
			end
			
			def attack()
				sum = sum_weapons()
				if sum!= nil
					return @strength + sum_weapons
				else 
					return @strength
				end
			end
			
			def defend(rA)
				return manage_hit(rA)
			end
			
			def receive_reward
				w_reward=Dice.weapons_reward()
				s_reward=Dice.shields_reward()
				for i in 1..w_reward
					wnew=new_weapon()
					receive_weapon(wnew)
				end
				for i in 1..s_reward
					snew=new_shield()
					receive_shield(snew)
				end
				extra_health=Dice.health_reward
				@health+=extra_health
			end
			
			def to_s
				return "P"+ super 
			end
			
	end
end
