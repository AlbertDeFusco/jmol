//Javascript
fig('1','quartz.png',200,200,500,500,'Quartz loaded without symmetry (<b>load quartz.cif</b>).')
fig('2','quartz_111.png',0,0,0,0,'Quartz loaded with symmetry applied, but not packed (<b>load quartz.cif {1 1 1}</b>).')
fig('3','quartz_packed.png',0,0,0,0,'Quartz loaded as a packed unit cell (<b>load quartz.cif PACKED</b>).')
fig('4','caffeine_21_21_21.png',0,0,0,0,'Caffeine model loaded as though it had <i>P2<sub>1</sub> 2<sub>1</sub> 2<sub>1</sub></i> symmetry (<b>load caffeine.xyz {1 1 1}  spacegroup "P 21 21 21"  unitcell {10.0 12.0 8.0 90 90 90}</b>).')
fig('5','quartz_xyz.png',0,0,0,0,'Quartz loaded with just one of its 3-fold screw axis operations (<b>load quartz.cif SPACEGROUP "-y,x-y,z-1/3"</b>)')
fig('6','NaCl_iso.png',0,0,0,0,'Surface through a plane parallel to the 111 Miller plane highlighting atom positions.')
