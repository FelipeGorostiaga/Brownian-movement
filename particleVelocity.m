function particleVelocity(output_file)
    fid = fopen(output_file);
    N = str2num(fgetl(fid));
    fskipl(fid);

    x =[];
    y = [];
    vx = [];
    vy = [];
    r =[];
    ids =[];

    while !feof(fid)
        s = textscan(fid, "%d %f %f %f %f %f", N);

        ids = [ids; s(1)];
        x = [x; s(2)];
        y = [y; s(3)];
        vx = [vx; s(4)];
        vy = [vy; s(5)];
        r = [r; s(6)];

        fskipl(fid);
        fskipl(fid);
    end

    a = floor(size(vx)(1) * 2/3);
    me = [];
    vel = [];

    for i = a:size(vx)
        vx1 = vx{i};
        vy1 = vy{i};
        ve = sqrt(vx1.^2 + vy1.^2);
        me = [me; mean(ve)];
        vel = [vel; ve];
    end
    
   

    k = 0:0.001:max(vel);

    cdf = empirical_cdf(k,vel);

    pdf = diff([0 cdf]);

    plot(k, pdf)
    
    xlabel('velocidades de las particulas (m/s)');
    ylabel('PDF');

end 