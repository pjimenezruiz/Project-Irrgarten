#encoding:utf-8

require_relative 'CombatElement'

module Irrgarten

	class Shield < CombatElement
			
		public
			def initialize (p ,u)	
				super(p,u)
			end
			
			def protect
				return produce_effect
			end
			
			def to_s
				return "S"+super
			end
	end
end
