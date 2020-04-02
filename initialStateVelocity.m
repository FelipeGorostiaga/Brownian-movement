function initialStateVelocity(output_file)
    fid = fopen(output_file);
    N = str2num(fgetl(fid));
    fskipl(fid);

    
    s = textscan(fid, "%d %f %f %f %f %f", N);

    ids = s{1};
    x = s{2};
    y = s{3};
    vx = s{4};
    vy = s{5};
    r = s{6};

     ve = sqrt(vx.^2 + vy.^2);

     k = 0:0.1:max(ve);


    cdf = empirical_cdf(k,ve);

    pdf = diff([0 cdf]);

    plot(k, cdf)
    

end